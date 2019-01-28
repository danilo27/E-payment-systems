package iss.controller;

import java.net.URISyntaxException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import iss.dto.AcqToPccDto;
import iss.dto.MerchantAccountDto;
import iss.dto.StringDTO;
import iss.dto.UserAccountDto;
import iss.services.PaymentRequestService;
import iss.services.PaymentService;
import iss.services.ValidationService;
import iss.model.Card;
import iss.model.Payment;
import iss.model.PaymentRequest;
import iss.model.enums.ReturnType;
import iss.model.enums.TransactionResult;
 
@RestController
@RequestMapping("/issBank")
public class IssPaymentController {
	
	@Autowired
	PaymentRequestService paymentRequestService;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	PaymentService paymentService;
	
	@Value("${bank.iin}")
	private String bankIin;
	
	@Value("${pcc.url}")
	private String pccUrl;
	
	@Value("${server.port}")
	private String iss_url;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<AcqToPccDto> redirectToExternalUrl(@RequestBody AcqToPccDto toPcc) throws URISyntaxException {
		System.out.println("[Issuer]");
		System.out.println(toPcc.toString());
		
		if(toPcc.getCard().getPan().startsWith(bankIin)){
			if(validationService.validateCardUserOnly(toPcc.getPr(), toPcc.getCard()) == ReturnType.SUCCESS){ 
				//url = "http://localhost:4200/payment-card-success";
				toPcc.setTransactionResult(TransactionResult.SUCCESS);
			} else {
				toPcc.setTransactionResult(TransactionResult.UNKNOWN_ERROR);
			} 
				
				// if (validationService.validateCard(pr, c) == ReturnType.FAILED)
				//url = pr.getFailedUrl();
			//else 
				//url = pr.getErrorUrl();
		}
		
		
		toPcc.setIssuer_timestamp(Calendar.getInstance().getTime());
		toPcc.setIss_url(iss_url); 
		
	    return new ResponseEntity<>(toPcc,HttpStatus.OK);
	}
 
}
