package central.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import central.model.Article;
import central.model.MagazinePaymentType;
import central.repository.ArticleRepository;
import central.repository.AuthorRepository;
import central.repository.IssueRepository;

 

@RestController
@RequestMapping(value = "/nc/file")
@CrossOrigin(origins = "http://localhost:4200")
public class UploadController {
	
	@Autowired 
	ArticleRepository articleRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	@PostMapping("/addArticle")
	public void addPaper(@RequestBody Article article) throws IOException{
		if(article.getIssue().getMagazine().getPaymentType()==MagazinePaymentType.PAID_ACCESS)
			article.setPrice(Double.parseDouble("5.0"));
		
		article.setAuthor(authorRepository.findAll().get(0));
		Article a = articleRepository.save(article);
		
		final File folder = new File("C:/articles");
	      
		PDFMergerUtility ut = new PDFMergerUtility();
		for(String s : listFilesForFolder(folder)){
			ut.addSource("C:/articles/"+s);
			 
		}
		
		PDDocument document = new PDDocument();
        document.addPage(new PDPage());
    
        document.save("C:/issues/"+issueRepository.findById(a.getIssue().getId()).orElse(null).getMagazine().getIssn()+"_"+a.getIssue().getDate()+".pdf");
       
        System.out.println("PDF created");
        document.close();
		    
		ut.setDestinationFileName("C:/issues/"+issueRepository.findById(a.getIssue().getId()).orElse(null).getMagazine().getIssn()+"_"+a.getIssue().getDate()+".pdf");
		ut.mergeDocuments();
		
		 
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/addFile")
	public HashMap<String, String> addFile(@RequestParam("file") MultipartFile file) throws IOException {
		
			HashMap<String, String> mmap=new HashMap<String,String>();
       		String filename=saveUploadedFile(file);
	      	//mmap.put("filename", filename);
	      	//return mmap;
	    
	      
		
			 
	
		
		  return mmap;
		
	}
		
	public List<String> listFilesForFolder(final File folder) {
	    List<String> ret = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	            ret.add(fileEntry.getName());
	        }
	    }
	    return ret;
	}
	
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
	    File convFile = new File( multipart.getOriginalFilename());
	    multipart.transferTo(convFile);
	    return convFile;
	}
		
	public String saveUploadedFile(MultipartFile file) throws IOException {
    	String retVal = null;
        if (! file.isEmpty()) {
            byte[] bytes = file.getBytes();
             
            Path path = Paths.get("C:/articles" + File.separator + file.getOriginalFilename());
            
            System.out.println(path);
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }
}
