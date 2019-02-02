package central.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import central.dto.StringDto;
import central.model.UserItem;
import central.repository.UserItemRepository;
import central.repository.UserRepository;

@RestController
@RequestMapping(value = "/nc")
@CrossOrigin(origins = {"http://localhost:4204"})
public class ProductController {
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserItemRepository userItemRepository;
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/items/{username}")
	public ResponseEntity<List<UserItem>> all(@PathVariable String username){
		return new ResponseEntity<List<UserItem>>(userRepository.findByUsername(username).orElse(null).getUserItems(), HttpStatus.OK);
	    
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/file", method = RequestMethod.POST, produces = "application/pdf")
	 
	public ResponseEntity<byte[]> getFile(@RequestBody StringDto dto) throws IOException {
	 ;

		Path pdfPath = Paths.get(dto.getValue());
		byte[] pdf = Files.readAllBytes(pdfPath);
		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    
	    String filename = "output.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<>(pdf, headers, HttpStatus.OK);
	    return response;
	}
	
//	@PreAuthorize("hasAuthority('USER')")
//	@RequestMapping(value = "/file/{fileUrl}", method = RequestMethod.GET, produces = "application/pdf")
//	 
//	public ResponseEntity<byte[]> getFile(@PathVariable String fileUrl) throws IOException {
//	 ;
//
//		Path pdfPath = Paths.get(fileUrl);
//		byte[] pdf = Files.readAllBytes(pdfPath);
//		
//		
//		HttpHeaders headers = new HttpHeaders();
//	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
//	    
//	    String filename = "output.pdf";
//	    headers.setContentDispositionFormData(filename, filename);
//	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//	    ResponseEntity<byte[]> response = new ResponseEntity<>(pdf, headers, HttpStatus.OK);
//	    return response;
//	}
}
