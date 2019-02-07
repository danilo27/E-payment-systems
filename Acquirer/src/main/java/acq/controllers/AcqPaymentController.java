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
		
		payment.setPaymentRequestId(pr.getId());
		 
		//payment.setPaymentId(paymentService.findAll().size());
		payment.setMerchantPassword(pr.getMerchantPassword());
		payment.setMessage("");
		payment.setPaymentRequestToken(pr.getToken());
		payment.setMerchantOrderId(pr.getMerchantOrderId());
		payment.setAmount(pr.getAmount());
		payment.setMerchantId(pr.getMerchantId());
		
		payment = paymentService.save(payment);
		
		if(validationService.validatePaymentRequest(payment) == ReturnType.SUCCESS){
			 
			payment.setPaymentUrl("http://localhost:4201/enter-buyer-details?t="+payment.getId());
			paymentRequestService.save(pr);
		} else {
			payment.setMessage("Error");
		}
		
		System.out.println("Returning payment: " + payment.toString());
		
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
			@PathVariable Long token){
		 
		//PaymentRequest pr = paymentRequestService.findByToken(token);
		Payment p = paymentService.findOne(token).orElse(null);
		System.out.println("u validate and ex : " + p.toString());
		Account merchant = accService.findByMerchantId(p.getMerchantId());
		c.setPan(c.getPan().replace(" ", ""));
		String url = "";
		Cart cart = new Cart();
		if(c.getPan().startsWith(bankIin)){
			if(validationService.validateCard(p, c) == ReturnType.SUCCESS){ 
				url ="http://localhost:4200/payment-card-success";
				cart.setId(p.getId());
				cart.setMerchantOrderId(p.getMerchantOrderId());
				cart.getItemDetails().put("merchantOrderId", p.getMerchantOrderId().toString());
				cart.getItemDetails().put("status", "success");
				cart.getItemDetails().put("successUrl", url);
				cart.setToken(p.getMerchantOrderId().toString());
				System.out.println("[ACQ] cart after success: " + cart.toString());
			} else if (validationService.validateCard(p, c) == ReturnType.FAILED){
				cart.getItemDetails().put("status", "failed");
				url = p.getFailedUrl();
			} else { 
				cart.getItemDetails().put("status", "error");
				url = p.getErrorUrl();
			}
		} else {
			//TODO PCC
			AcqToPccDto toPcc = new AcqToPccDto();
			toPcc.setCard(c);
			toPcc.setAcquirer_timestamp(Calendar.getInstance().getTime());
			toPcc.setAcquirer_order_id(p.getId());
			toPcc.setAcq_url(acq_url);
			toPcc.setPr(p);
			String fooResourceUrl = pccUrl+"/pcc/forwardToIssuer";
			ResponseEntity<AcqToPccDto> response = restTemplate().postForEntity(fooResourceUrl, toPcc, AcqToPccDto.class);
			if(((AcqToPccDto)response.getBody()).getTransactionResult()==TransactionResult.SUCCESS){
				
				 
				url = "http://localhost:4200/payment-card-success";
				cart.setId(p.getId());
				cart.setMerchantOrderId(p.getMerchantOrderId());
				cart.getItemDetails().put("merchantOrderId", p.getMerchantOrderId().toString());
				cart.getItemDetails().put("status", "success");
				cart.getItemDetails().put("successUrl", url);
				cart.setToken(p.getMerchantOrderId().toString());
				System.out.println("[ACQ] cart after success: " + cart.toString());
							
			} else {
				url = p.getErrorUrl();
				cart.getItemDetails().put("status", "error");
			} 
		}
	
		if(merchant!=null && cart.getItemDetails().get("status").equals("success")){
			System.out.println("[ACQ] Merchant Balance - Before: " + merchant.getAccountBalance());
			merchant.setAccountBalance(merchant.getAccountBalance()+p.getAmount());
			System.out.println("[ACQ] Merchant Balance - After: " + merchant.getAccountBalance());
			accService.save(merchant);
		}
		System.out.println("[ACQ] end Cart" + cart.toString());
		
		//move to PC proceed
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
		merchantService.save(m);
		System.out.println("[ACQ] New Merchant: " + m.toString());
		
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
	public ResponseEntity<Cart> getCart(@PathVariable Long token) throws URISyntaxException{
		System.out.println("[Acq] getCart, token: " + token);
		//PaymentRequest pr = paymentRequestService.findOne(token).orElse(null);
		Payment p = paymentService.findOne(token).orElse(null);
		//System.out.println("pr: " + pr.toString());
		Cart cart = new Cart();
		cart.setTotalPrice(p.getAmount());
		cart.setId(Long.valueOf(p.getMerchantOrderId()));
		cart.getItemDetails().put("merchantOrderId", p.getMerchantOrderId().toString());
	    return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	    
	}
}
