package pcc.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pcc.model.Payment;
import pcc.dto.AcqToPccDto;
import pcc.model.Bank;
import pcc.repositories.BankRepository;
import pcc.repositories.PaymentRepository;

 

@RestController
@RequestMapping("/pcc")
public class PccController {
	
//	@Value("${bank.iin}")
//	private String bankIin;
//	
//	@Value("${pcc.url}")
//	private String pccUrl;
	
	@Autowired
	BankRepository bankRepository;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired 
	BankRepository bankRepo;
	
	@Autowired
	private PaymentRepository paymentService;
	
	@PostMapping("/forwardToIssuer")
	public ResponseEntity<AcqToPccDto> redirectToExternalUrl(@RequestBody AcqToPccDto toPcc) throws URISyntaxException {
		System.out.println("[PCC]");
		Bank bank = bankRepo.findByIin(toPcc.getCard().getPan().toString().substring(0, 6)); 
		 
		toPcc.setIssuer_timestamp(Calendar.getInstance().getTime());
 
		Payment p = new Payment();
		p = toPcc.getPr();
		p.setId(null);
		p.setIssuerTimestamp(toPcc.getIssuer_timestamp());
		p = paymentService.save(p);
		
		ResponseEntity<AcqToPccDto> response = restTemplate().postForEntity(
				bank.getUrl() + "/issBank/transaction", 
				toPcc, AcqToPccDto.class); 
 
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	
	@GetMapping("/getBanks")
	public ResponseEntity<List<String>> getBanks() throws URISyntaxException {
		System.out.println("[PCC] getBanks");
		List<String> banks = new ArrayList<String>();
		for(Bank b : bankRepository.findAll()){
			banks.add(b.getUrl());
		}
 		return new ResponseEntity<List<String>>(banks, HttpStatus.OK);
	}
}
