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

import pc.model.PaymentRequest;
import pc.payments.card.URL_ID_DTO;
import pc.services.PaymentRequestService;

@RestController
@RequestMapping("/api")
public class CardPaymentController {
	
	@Autowired
	private PaymentRequestService paymentRequestService;
	
	@Value("${bank.acquirer}")
	private String bankAcquirer;
	
	@Value("${bank.issuer}")
	private String bankIssuer;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
 
	@PostMapping("/requestPayment2")
	public ResponseEntity<?> requestPayment2(@RequestBody PaymentRequest request){
		PaymentRequest req = paymentRequestService.save(request);
		System.out.println("PaymentRequest: " + req.toString());
		System.out.println("forwarding PaymentRequest to the Acquirer bank on port: " + bankAcquirer);
	
		HttpHeaders headers = new HttpHeaders();
		String fooResourceUrl = bankAcquirer+"/api/to-be-redirected";
		ResponseEntity<String> response = restTemplate().getForEntity(fooResourceUrl, String.class);
		
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}
 
	@PostMapping("/requestPayment")
	public ResponseEntity<URL_ID_DTO> requestPayment(@RequestBody PaymentRequest request) throws URISyntaxException{
		PaymentRequest req = paymentRequestService.save(request);
		System.out.println("PaymentRequest: " + req.toString());
		System.out.println("forwarding PaymentRequest to the Acquirer bank on port: " + bankAcquirer);
		
		String fooResourceUrl = bankAcquirer+"/acqBank/to-be-redirected";
		ResponseEntity<URL_ID_DTO> response = restTemplate().getForEntity(fooResourceUrl, URL_ID_DTO.class);
		//URL_ID_DTO dto = (URL_ID_DTO)response.getBody();
	
	    return response;
	}
	
	@RequestMapping("/to-be-redirected")
	public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
	    URI yahoo = new URI("http://localhost:4201");
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(yahoo);
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	@GetMapping
	public ResponseEntity<?> proceed(@RequestBody PaymentRequest request){
		PaymentRequest req = paymentRequestService.save(request);
		
		//forward PaymentRequest to the bank

		return new ResponseEntity<>(HttpStatus.FOUND);
	}
}
