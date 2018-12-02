package acq.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import acq.dto.URL_ID_DTO;
import acq.model.PaymentRequest;
import acq.services.PaymentRequestService;

@RestController
@RequestMapping("/api")
public class AcqPaymentController {
	
	@Autowired
	PaymentRequestService paymentRequestService;
	
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
	
	@RequestMapping(
			value = "/check",
			method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public void check(){
		System.out.println("Checked");
	}
}
