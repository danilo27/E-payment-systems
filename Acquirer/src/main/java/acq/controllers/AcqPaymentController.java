package acq.controllers;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import acq.dto.AcqToPccDto;
import acq.dto.MerchantAccountDto;
import acq.dto.StringDTO;
import acq.dto.UserAccountDto;
import acq.model.Account;
import acq.model.Card;
import acq.model.Merchant;
import acq.model.Payment;
import acq.model.PaymentRequest;
import acq.model.enums.ReturnType;
import acq.model.enums.TransactionResult;
import acq.services.AccountService;
import acq.services.MerchantService;
import acq.services.PaymentRequestService;
import acq.services.PaymentService;
import acq.services.ValidationService;
import acq.model.Cart;
 
@RestController
@RequestMapping("/acqBank")
public class AcqPaymentController {
	
	@Autowired
	PaymentRequestService paymentRequestService;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	private AccountService accService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Value("${bank.iin}")
	private String bankIin; //iin acquirer-a
	
	@Value("${pc.url}")
	private String pcUrl;
	
	@Value("${pcc.url}")
	private String pccUrl;
	
	@Value("${server.port}")
	private String acq_url;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@PostMapping("/getUrlAndId")
	public Payment redirectToExternalUrl(@RequestBody PaymentRequest pr) throws URISyntaxException {
		System.out.println("[ACQ] " + pr.toString());
		Payment payment = new Payment();
 
		if(validationService.validatePaymentRequest(pr) == ReturnType.SUCCESS){
			payment.setPaymentRequestId(pr.getId());
			payment.setPaymentUrl("http://localhost:4201/enter-buyer-details");
			payment.setPaymentId(paymentService.findAll().size());
			payment.setMessage("");
			payment.setPaymentRequestToken(pr.getToken());
			
			paymentService.save(payment);
			paymentRequestService.save(pr);
		} else {
			payment.setMessage("Error");
		}
		
	    return payment;
	}
	
	@RequestMapping(path = "/sendPaymentRequest", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> requestPayment(@RequestBody PaymentRequest request){
		PaymentRequest req = paymentRequestService.save(request);
		
		//TODO validate request
		
		//TODO generate PAYMENT_URL String 256 and PAYMENT_ID Number 10
		//and redirect buyer to acquirer site
		
		System.out.println("Recieved request");
		
		return new ResponseEntity<>(HttpStatus.FOUND);
	}
	
	@PostMapping("/validateAndExecute/{token}")
	public ResponseEntity<?> validateCardAndExecute(
			@RequestBody Card c,
			@PathVariable String token){
		 
		PaymentRequest pr = paymentRequestService.findByToken(token);
		System.out.println("u validate and ex : " + pr.toString());
		Account merchant = accService.findByMerchantId(pr.getMerchantId());
		c.setPan(c.getPan().replace(" ", ""));
		String url = "";
		Cart cart = new Cart();
		if(c.getPan().startsWith(bankIin)){
			if(validationService.validateCard(pr, c) == ReturnType.SUCCESS){ 
				url ="http://localhost:4200/payment-card-success";
				cart.setId(pr.getId());
				cart.setMerchantOrderId(pr.getMerchantOrderId());
				cart.getItemDetails().put("merchantOrderId", pr.getMerchantOrderId().toString());
				cart.getItemDetails().put("status", "success");
				cart.getItemDetails().put("successUrl", url);
				cart.setToken(pr.getMerchantOrderId().toString());
				System.out.println("[ACQ] cart after success: " + cart.toString());
			} else if (validationService.validateCard(pr, c) == ReturnType.FAILED)
				url = pr.getFailedUrl();
			else 
				url = pr.getErrorUrl();
		} else {
			//TODO PCC
			AcqToPccDto toPcc = new AcqToPccDto();
			toPcc.setCard(c);
			toPcc.setAcquirer_timestamp(Calendar.getInstance().getTime());
			toPcc.setAcquirer_order_id(pr.getId());
			toPcc.setAcq_url(acq_url);
			toPcc.setPr(pr);
			String fooResourceUrl = pccUrl+"/pcc/forwardToIssuer";
			ResponseEntity<AcqToPccDto> response = restTemplate().postForEntity(fooResourceUrl, toPcc, AcqToPccDto.class);
			if(((AcqToPccDto)response.getBody()).getTransactionResult()==TransactionResult.SUCCESS){
				
				 
				url = "http://localhost:4200/payment-card-success";
				cart.setId(pr.getId());
				cart.setMerchantOrderId(pr.getMerchantOrderId());
				cart.getItemDetails().put("merchantOrderId", pr.getMerchantOrderId().toString());
				cart.getItemDetails().put("status", "success");
				cart.getItemDetails().put("successUrl", url);
				cart.setToken(pr.getMerchantOrderId().toString());
				System.out.println("[ACQ] cart after success: " + cart.toString());
							
			} else {
				url = pr.getErrorUrl();
			}
		}
	
		if(merchant!=null){
			System.out.println("[ACQ] Merchant Balance - Before: " + merchant.getAccountBalance());
			merchant.setAccountBalance(merchant.getAccountBalance()+pr.getAmount());
			System.out.println("[ACQ] Merchant Balance - After: " + merchant.getAccountBalance());
			accService.save(merchant);
		}
		System.out.println("[ACQ] end Cart" + cart.toString());
		ResponseEntity<Boolean> res = restTemplate().postForEntity(pcUrl+"/api/pc/returnToPc", cart, Boolean.class);
	    
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@GetMapping("/makeMerchantAccount")
	public ResponseEntity<MerchantAccountDto> makeMerchantAccount( 
			//@RequestBody MerchantAccountDto acc
			){
		Merchant m = new Merchant();
		m.setMerchantBankUrl("http://localhost:"+acq_url);
		m.setMerchantPass(UUID.randomUUID().toString());
		m = merchantService.save(m);
		m.setMerchantId(m.getId().toString());
		
		Account a = new Account();
		a.setAccountBalance(0);
		a.setMerchantId(m.getMerchantId());
		MerchantAccountDto dto = new MerchantAccountDto();
		dto.setMerchantId(m.getMerchantId().toString());
		dto.setMerchantPassword(m.getMerchantPass());
		return new ResponseEntity<MerchantAccountDto>(dto, HttpStatus.OK);
	}
	
	@PostMapping("/makeUserAccount")
	public ResponseEntity<?> makeUserAccount(
			@RequestBody UserAccountDto acc
			){

		return new ResponseEntity<>(new UserAccountDto(), HttpStatus.OK);
	}
	
	@GetMapping("/getCart/{token}")
	public ResponseEntity<Cart> getCart(@PathVariable String token) throws URISyntaxException{
		System.out.println("[Acq] getCart, token: " + token);
		PaymentRequest pr = paymentRequestService.findByToken(token);
		System.out.println("pr: " + pr.toString());
		Cart cart = new Cart();
		cart.setTotalPrice(pr.getAmount());
		cart.setId(Long.valueOf(pr.getMerchantOrderId()));
		cart.getItemDetails().put("merchantOrderId", pr.getMerchantOrderId().toString());
	    return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	    
	}
}
