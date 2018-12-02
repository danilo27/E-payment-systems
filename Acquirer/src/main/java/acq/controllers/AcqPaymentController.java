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

import acq.dto.StringDTO;
import acq.dto.URL_ID_DTO;
import acq.model.Card;
import acq.model.PaymentRequest;
import acq.services.PaymentRequestService;
import acq.services.ValidationService;
 
@RestController
@RequestMapping("/acqBank")
public class AcqPaymentController {
	
	@Autowired
	PaymentRequestService paymentRequestService;
	
	@Autowired
	ValidationService validationService;
	
	@Value("${bank.iin}")
	private String bankIin;
	
	@RequestMapping("/to-be-redirected")
	public URL_ID_DTO redirectToExternalUrl() throws URISyntaxException {
		
		URL_ID_DTO dto = new URL_ID_DTO();
		
		//TODO validate request
		if(true){
			dto.setPaymentUrl("http://localhost:4201");
			dto.setPaymentId(0); // size of ids in repo
		} else {
			dto.setPaymentUrl("");
			dto.setPaymentId(-1);
		}
		
	    return dto;
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
		System.out.println(c.getPan());
		c.setPan(c.getPan().replace(" ",""));
		System.out.println(c.getPan());
		HttpHeaders headers = new HttpHeaders();
		PaymentRequest pr = paymentRequestService.findByToken(token);
		String url = "";
		if(c.getPan().startsWith(bankIin)){
			if(validationService.validate(pr, c).equals("SUCCESS")){
				//headers.add("Location", pr.getSuccessUrl());
				System.out.println("IIN ok and success");
				//headers.add("Location", "http://localhost:4201/success");
				headers.setLocation(URI.create("http://localhost:4201/success"));
				url = "http://localhost:4201/success";
			} else if (validationService.validate(pr, c).equals("FAILED"))
				headers.add("Location", pr.getFailedUrl());
			else 
				headers.add("Location", pr.getErrorUrl());
		} else {
			//TODO PCC
		}
	
	    
		return new ResponseEntity<>(new StringDTO(url), HttpStatus.OK);
	}
}
