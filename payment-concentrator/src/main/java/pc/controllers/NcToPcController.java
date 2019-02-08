package pc.controllers;

import java.net.URISyntaxException;
import java.util.List;

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

import pc.dto.SubscriptionRequest;
import pc.model.Cart;
import pc.model.PaymentType;
import pc.payments.impl.CardService;
import pc.repositories.CartRepository;
import pc.repositories.MerchantInfoRepository;
import pc.repositories.MerchantRepository;


@RestController
@RequestMapping("/pc")
@CrossOrigin
public class NcToPcController {
		
	@Autowired
	private CardService cardService;
	
	@Autowired
	private MerchantInfoRepository merchantInfoRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Value("${nc.url}")
	private String ncUrl;
	
	@Value("${pcc.url}")
	private String pccUrl;
	
	@Bean
	public RestTemplate restTemp() {
	    return new RestTemplate();
	}
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@PostMapping("/sendCart")
	public ResponseEntity<Cart> sendCart(@RequestBody Cart cart) throws URISyntaxException{
		System.out.println("[PC] " + cart.toString());
		
		String merchantpas = merchantInfoRepository.findMerchantData("Card", cart.getMerchantId(), "merchantPassword").getValue();
		cart.setMerchantPassword(merchantpas);
		cart = cartRepository.save(cart);//promenice id, ali ce merchantOrderId ostati kao u NC
		System.out.println("Cart in PC: " + cart.toString());
	     
	    return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	    
	}
	

	
	@PostMapping("/returnToPc")
	public ResponseEntity<Boolean> returnToPc(@RequestBody Cart cart) throws URISyntaxException{
		System.out.println("[PC] finishing:" + cart.toString());
		
		return (ResponseEntity<Boolean>) restTemp().postForEntity(ncUrl+"/api/nc/transaction/returnToNc", cart, Boolean.class);
		 
	    
	}
 
	@GetMapping("/getBanks")
	public ResponseEntity<List<String>> getBanks() throws URISyntaxException {
		ResponseEntity<List> response = restTemp().getForEntity(pccUrl+"/pcc/getBanks", List.class);
		return new ResponseEntity<List<String>>(response.getBody(), HttpStatus.OK);
	}
	
 
	
}