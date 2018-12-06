package acq.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import acq.dto.MerchantAccountDto;
import acq.dto.StringDTO;
import acq.dto.URL_ID_DTO;
import acq.dto.UserAccountDto;
import acq.model.Card;
import acq.model.Payment;
import acq.model.PaymentRequest;
import acq.model.enums.ReturnType;
import acq.services.PaymentRequestService;
import acq.services.PaymentService;
import acq.services.ValidationService;
 
@RestController
@RequestMapping("/acqBank")
public class AcqPaymentController {
	
	@Autowired
	PaymentRequestService paymentRequestService;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	PaymentService paymentService;
	
	@Value("${bank.iin}")
	private String bankIin;
	
	@PostMapping("/getUrlAndId")
	public Payment redirectToExternalUrl(@RequestBody PaymentRequest pr) throws URISyntaxException {
		System.out.println(pr.toString());
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
		c.setPan(c.getPan().replace(" ", ""));
		String url = "";
		if(c.getPan().startsWith(bankIin)){
			if(validationService.validateCard(pr, c) == ReturnType.SUCCESS){ 
				url = "http://localhost:4201/success";
			} else if (validationService.validateCard(pr, c) == ReturnType.FAILED)
				url = pr.getFailedUrl();
			else 
				url = pr.getErrorUrl();
		} else {
			//TODO PCC
		}
	
	    
		return new ResponseEntity<>(new StringDTO(url), HttpStatus.OK);
	}
	
	@PostMapping("/makeMerchantAccount")
	public ResponseEntity<?> makeMerchantAccount( 
			@RequestBody MerchantAccountDto acc
			){
		//TODO create MERCHANT_ID and MERCHANT_PASSWORD
		return new ResponseEntity<>(new MerchantAccountDto(), HttpStatus.OK);
	}
	
	@PostMapping("/makeUserAccount")
	public ResponseEntity<?> makeUserAccount(
			@RequestBody UserAccountDto acc
			){

		return new ResponseEntity<>(new UserAccountDto(), HttpStatus.OK);
	}
}
