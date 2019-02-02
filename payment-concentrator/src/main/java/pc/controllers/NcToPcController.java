package pc.controllers;

import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pc.model.Cart;
import pc.payments.card.CardService;
import pc.repositories.CartRepository;

@RestController
@RequestMapping("/pc")
@CrossOrigin
public class NcToPcController {
		
	@Autowired
	private CardService cardService;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Value("${nc.url}")
	private String ncUrl;
	
	@Bean
	public RestTemplate restTemp() {
	    return new RestTemplate();
	}
 
	@PostMapping("/sendCart")
	public ResponseEntity<Cart> sendCart(@RequestBody Cart cart) throws URISyntaxException{
		System.out.println("[PC] " + cart.toString());
		cart.getItemDetails().put("pcUrl", "http://localhost:4200");
		String uuid = UUID.randomUUID().toString();
		cart.setToken(uuid);
		cartRepository.save(cart);
		
	     
	    return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	    
	}
	
	@GetMapping("/getCart/{token}")
	public ResponseEntity<Cart> getCart(@PathVariable String token) throws URISyntaxException{
		System.out.println("[PC] getCart, token: " + token);
	    return new ResponseEntity<Cart>(cartRepository.findByToken(token), HttpStatus.OK);
	    
	}
	
	@PostMapping("/returnToPc")
	public ResponseEntity<Boolean> returnToPc(@RequestBody Cart cart) throws URISyntaxException{
		System.out.println("[PC] finishing:" + cart.toString());
		
		return (ResponseEntity<Boolean>) restTemp().postForEntity(ncUrl+"/api/nc/transaction/returnToNc", cart, Boolean.class);
		 
	    
	}
 
	 
	
	 
	
}