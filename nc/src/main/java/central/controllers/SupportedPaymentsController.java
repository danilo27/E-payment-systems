package central.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import central.dto.MerchantCredentialsDto;
import central.model.SupportedPayments;
import central.repository.SupportedPaymentsRepository;

@RestController
@RequestMapping(value = "/nc/supported-payments")
public class SupportedPaymentsController {

	
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@GetMapping("/all")
	public ResponseEntity<?> all(){	
		String fooResourceUrl = "http://localhost:8080/api/payment-types/all";
		RestTemplate rt = new RestTemplate();
		ResponseEntity<SupportedPayments[]> response = rt.getForEntity(fooResourceUrl, SupportedPayments[].class);
		SupportedPayments[] supportedPayments = response.getBody();
		/*
		for(SupportedPayments sp : supportedPayments){
			System.out.println(sp.getName());
		}*/
		
		return new ResponseEntity<>(supportedPayments, HttpStatus.OK);
	    
	}
}
