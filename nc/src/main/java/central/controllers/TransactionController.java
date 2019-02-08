package central.controllers;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import central.dto.StringDto;
import central.dto.SubscriptionRequest;
import central.dto.TransactionResult;
import central.model.Article;
import central.model.Cart;
import central.model.Issue;
 
import central.model.User;
import central.model.UserItem;
import central.repository.ArticleRepository;
import central.repository.CartRepository;
import central.repository.IssueRepository;
 
import central.repository.MagazineRepository;
import central.repository.UserItemRepository;
import central.repository.UserRepository;

@RestController
@RequestMapping(value = "/nc/transaction")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4204"})
public class TransactionController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserItemRepository userItemRepository;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
 
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/sendSubscription")
	public ResponseEntity<TransactionResult> sendSubscription(@RequestBody SubscriptionRequest req) {
		
		String fooResourceUrl = "http://localhost:8080/api/payment/prepare/subscription/Paypal";
		ResponseEntity<TransactionResult> response = restTemplate().postForEntity(fooResourceUrl, req, TransactionResult.class);

	    return response;
	   
	}
	
	//redirect
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/proceedToPc")
	public ResponseEntity<StringDto> proceedToPc(@RequestBody Cart cart){
		cart.getItemDetails().put("status", "inProcess");
		cart.setMerchantId(cart.getItemDetails().get("merchantId"));
		cart.setMerchantPassword(cart.getItemDetails().get("merchantPas"));
		cart.setStatus("inProgress");
		cart = cartRepository.save(cart);
		cart.setMerchantOrderId(cart.getId());
		cart.setMerchantTimestamp(Calendar.getInstance().getTime());
		cart = cartRepository.save(cart);
		 
		 
		String fooResourceUrl = "http://localhost:8080/api/pc/sendCart";
		ResponseEntity<Cart> response = restTemplate().postForEntity(fooResourceUrl, cart, Cart.class);//notify PC to save new transaction
		//cart.setToken(response.getBody().getToken());
		//cart = cartRepository.save(cart);
		
		//cart = cartRepository.save(response.getBody());
		//StringDto dto = new StringDto("value",response.getBody().getItemDetails().get("pcUrl")+"?t="+response.getBody().getToken());
		Long cartIdInPc = response.getBody().getId();
		StringDto dto = new StringDto("value","http://localhost:4200?t="+cartIdInPc); //id cart-a u PC-u!
		System.out.println("[NC]" + dto.toString());
		return new ResponseEntity<StringDto>(dto, HttpStatus.OK);
	}
	
	//credit card
	@PostMapping("/returnToNc")
	public ResponseEntity<Boolean> returnToPc(@RequestBody Cart cart) throws URISyntaxException{
		System.out.println("[NC] finished:" + cart.toString()); 
		Cart c = cartRepository.findByMerchantOrderId(cart.getMerchantOrderId());
		if(cart.getStatus().equals("success")){
			c.setStatus("success");
			cartRepository.save(c);
			if(c!=null){
				c.getItemDetails().put("status", "success");
				User user = userRepository.findByUsername(c.getItemDetails().get("username")).orElse(null);
				if(user!=null){ 
					user.getUserItems().add(getItem(c));
					user = userRepository.save(user);
					System.out.println("User: " + user.getUsername() +" - Items: " + user.getUserItems().toString());
					return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				}
				
			}
		} else if(cart.getStatus().equals("failed")){
			c.setStatus("failed");
			cartRepository.save(c);
		} else {
			c.setStatus("error");
			cartRepository.save(c);
		}
		  
		
	     
	    return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	    
	}
	
	public UserItem getItem(Cart c){
		String type = "";
		UserItem item = new UserItem();
		if(c.getItemDetails().get("itemType").equals("issue")){
			 
			Issue issue = issueRepository.findById(Long.parseLong(c.getItemDetails().get("itemId"))).orElseGet(null);
			if (issue!=null){
				 
				item.setItemUrl(issue.getFilepath());
				item.setItemType("issue");
				item.setItemName(issue.getMagazine().getName() + " - " + issue.getDate());
			} 
		} else if (c.getItemDetails().get("itemType").equals("subscription")){
			 
		} else if (c.getItemDetails().get("itemType").equals("article")){
			Article article = articleRepository.findById(Long.parseLong(c.getItemDetails().get("itemId"))).orElse(null);
			if(article!=null){
				item.setItemUrl(article.getFilepath());
				item.setItemType("article");
			}
		}
		item = userItemRepository.save(item);
		return item;
	}
}
