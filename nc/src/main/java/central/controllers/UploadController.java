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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 

@RestController
@RequestMapping(value = "/nc/file")
@CrossOrigin(origins = "http://localhost:4200")
public class UploadController {
	@SuppressWarnings("deprecation")
	@PostMapping("/addFile")
	public HashMap<String, String> addFile(@RequestParam("file") MultipartFile file) throws IOException {
		
			HashMap<String, String> mmap=new HashMap<String,String>();
       		String filename=saveUploadedFile(file);
	      	//mmap.put("filename", filename);
	      	//return mmap;
	    
	      	final File folder = new File("C:/pdfs");
			      
			PDFMergerUtility ut = new PDFMergerUtility();
			for(String s : listFilesForFolder(folder)){
				ut.addSource("C:/pdfs/"+s);
				 
			}
			    
			ut.setDestinationFileName("C:/issues/issue1.pdf");
			ut.mergeDocuments();
		
			 
	
		
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
             
            Path path = Paths.get("C:/pdfs" + File.separator + file.getOriginalFilename());
            
            System.out.println(path);
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }
}
