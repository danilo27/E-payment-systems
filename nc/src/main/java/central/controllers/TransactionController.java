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

import central.model.Cart;
import central.dto.StringDto;

@RestController
@RequestMapping(value = "/nc/transaction")
@CrossOrigin(origins = "http://localhost:4204")
public class TransactionController {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
 
	
	//redirect
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/proceedToPc")
	public ResponseEntity<StringDto> proceedToPc(@RequestBody Cart cart){
		String fooResourceUrl = "http://localhost:8080/api/pc/sendCart";
		ResponseEntity<Cart> response = restTemplate().postForEntity(fooResourceUrl, cart, Cart.class);
		StringDto dto = new StringDto("value",response.getBody().getItemDetails().get("pcUrl")+"?t="+response.getBody().getToken());
		System.out.println("[NC]" + dto.toString());
		return new ResponseEntity<StringDto>(dto, HttpStatus.OK);
	}
	 
}
