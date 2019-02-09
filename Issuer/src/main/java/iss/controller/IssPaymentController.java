package iss.controller;

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

import iss.dto.AcqToPccDto;
import iss.model.IssueOrder;
import iss.model.Payment;
import iss.model.enums.ReturnType;
import iss.model.enums.TransactionResult;
import iss.repositories.IssueOrderRepository;
import iss.services.PaymentRequestService;
import iss.services.PaymentService;
import iss.services.ValidationService;
 
@RestController
@RequestMapping("/issBank")
public class IssPaymentController {
	
	@Autowired
	PaymentRequestService paymentRequestService;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	IssueOrderRepository issueOrderRepository;
	
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
		
		toPcc.setIssuer_timestamp(Calendar.getInstance().getTime());
		toPcc.setIss_url(iss_url); 
		
		Payment p = new Payment();
		p = toPcc.getPr();
		p.setId(null);
		p.setIssuerTimestamp(toPcc.getIssuer_timestamp());
		p = paymentService.save(p);
		
		if(toPcc.getCard().getPan().startsWith(bankIin)){
			if(validationService.validateCardUserOnly(toPcc.getPr(), toPcc.getCard()) == ReturnType.SUCCESS){ 
				toPcc.setTransactionResult(TransactionResult.SUCCESS);
				IssueOrder issueOrder= new IssueOrder();
				issueOrder = issueOrderRepository.save(issueOrder);
				toPcc.setIssuer_order_id(issueOrder.getId());
				p.setMessage("success");
			} else if(validationService.validateCardUserOnly(toPcc.getPr(), toPcc.getCard()) == ReturnType.FAILED){ 
				toPcc.setTransactionResult(TransactionResult.FAILED);
				p.setMessage("failed");
			} else { 
				toPcc.setTransactionResult(TransactionResult.ERROR);	
				p.setMessage("error");
			}
		}
		System.out.println(toPcc.toString());
		paymentService.save(p);
		 
		
	    return new ResponseEntity<>(toPcc,HttpStatus.OK);
	}
 
}
