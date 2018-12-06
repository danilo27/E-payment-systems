package pc.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import pc.dto.PaymentConfirmationDto;
import pc.dto.StringDto;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.card.CardService;
import pc.payments.card.URL_ID_DTO;
import pc.services.PaymentRequestService;

@RestController
@RequestMapping("/card")
public class CardPaymentController {
		
	@Autowired
	private CardService cardService;
	
	@PostMapping("/prepare")
	public ResponseEntity<?> preparePayment(@RequestBody PaymentRequest request) throws URISyntaxException{
		
		return cardService.prepareTransaction(request).getResponse();
	    
	}
	
	@PostMapping("/proceed")
	public TransactionResult proceedPayment(@RequestBody PaymentConfirmationDto request) throws URISyntaxException{
	
		return cardService.proceedTransaction(request);
	}
	
}
