package pcc.controllers;

import java.net.URISyntaxException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pcc.dto.AcqToPccDto;
import pcc.model.Bank;
import pcc.repositories.BankRepository;

 

@RestController
@RequestMapping("/pcc")
public class PccController {
	
//	@Value("${bank.iin}")
//	private String bankIin;
//	
//	@Value("${pcc.url}")
//	private String pccUrl;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired 
	BankRepository bankRepo;
	
	@PostMapping("/forwardToIssuer")
	public ResponseEntity<AcqToPccDto> redirectToExternalUrl(@RequestBody AcqToPccDto toPcc) throws URISyntaxException {
		System.out.println("[PCC]");
		Bank bank = bankRepo.findByIin(toPcc.getCard().getPan().toString().substring(0, 6)); 
		 
		//TODO evidentirati zahtev
		
		ResponseEntity<AcqToPccDto> response = restTemplate().postForEntity(
				bank.getUrl() + "/issBank/transaction", 
				toPcc, AcqToPccDto.class); 
 
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
}
