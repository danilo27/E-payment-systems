package central.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import central.dto.CartDto;
import central.dto.StringDto;

@RestController
@RequestMapping(value = "/nc/transaction")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
//	@PreAuthorize("hasAuthority('USER')")
//	@PostMapping(value="/proceedToPc")
//	public StringDto proceedToPc(@RequestBody CartDto cartDto){
//		String fooResourceUrl = "http://localhost:8080/api/pc/sendCart";
//		ResponseEntity<String> response = restTemplate().postForEntity(fooResourceUrl, cartDto, String.class);
//		
//		 
//		
//		System.out.println("[NC]" + response.getBody());
//		return new StringDto("url",response.getBody());
//	}
	
	//redirect
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/proceedToPc")
	public ModelAndView proceedToPc(@RequestBody CartDto cartDto){
		String fooResourceUrl = "http://localhost:8080/api/pc/sendCart";
		ResponseEntity<ModelAndView> response = restTemplate().postForEntity(fooResourceUrl, cartDto, ModelAndView.class);
		
		 
		
		System.out.println("[NC]" + response.getBody());
		return response.getBody();
	}
	 
}
