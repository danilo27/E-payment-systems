package central.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import central.dto.MerchantCredentialsDto;
import central.model.PaymentType;
import central.model.PaymentTypeField;
import central.model.SupportedPayments;
import central.repository.SupportedPaymentsRepository;
import javassist.NotFoundException;
 

@RestController
@RequestMapping(value = "/nc/supported-payments")
public class SupportedPaymentsController {
	
	@Value("${pc.url}")
	private String pcUrl;
	
//	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
//	@GetMapping("/all")
//	public ResponseEntity<?> all(){	
//		String fooResourceUrl = pcUrl+"/api/payment-types/all";
//		RestTemplate rt = new RestTemplate();
//		ResponseEntity<SupportedPayments[]> response = rt.getForEntity(fooResourceUrl, SupportedPayments[].class);
//		SupportedPayments[] supportedPayments = response.getBody();
//		
//		for(SupportedPayments sp : supportedPayments) 
//			System.out.println(sp.getName());
//		
//		
//		return new ResponseEntity<>(supportedPayments, HttpStatus.OK);
//	    
//	}
	
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@GetMapping("/all")
	public ResponseEntity<List<PaymentType>> all(){	
		String fooResourceUrl = pcUrl+"/api/payment-types/all";
		RestTemplate rt = new RestTemplate();
		ResponseEntity response = rt.getForEntity(fooResourceUrl, List.class);
	 
		
		
		return new ResponseEntity<List<PaymentType>>((List<PaymentType>)response.getBody(), HttpStatus.OK);
	    
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@GetMapping("/fields/{id}")
	public ResponseEntity<List<PaymentTypeField>> fields(@PathVariable String id) throws NotFoundException{
		RestTemplate rt = new RestTemplate();
		ResponseEntity response = rt.getForEntity(pcUrl+"/api/payment-types/fields/"+id, List.class);
		return response;
	}
}
