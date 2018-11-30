package pc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pc.model.PaymentRequest;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
	@PostMapping
	public ResponseEntity<?> requestPayment(@RequestBody PaymentRequest request){
		
		return new ResponseEntity<>(HttpStatus.FOUND);
	}
}
