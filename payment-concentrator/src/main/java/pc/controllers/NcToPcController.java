package pc.controllers;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import pc.dto.CartDto;
import pc.dto.PaymentConfirmationDto;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.card.CardService;

@RestController
@RequestMapping("/pc")
@CrossOrigin
public class NcToPcController {
		
	@Autowired
	private CardService cardService;
	
	//redirect
	@PostMapping("/sendCart")
	public ResponseEntity<ModelAndView> sendCart(@RequestBody CartDto cartDto) throws URISyntaxException{
		System.out.println("[PC] " + cartDto.toString());
		ModelAndView modelAndView = new ModelAndView("redirect:" + "http://localhost:4200");
	    modelAndView.addObject("cartDto",cartDto);
	     
	    return new ResponseEntity<ModelAndView>(modelAndView, HttpStatus.TEMPORARY_REDIRECT);
	    
	}
	
//	@PostMapping("/sendCart")
//	public String sendCart(@RequestBody CartDto cartDto) throws URISyntaxException{
//		System.out.println("[PC] " + cartDto.toString());
//		 
//	 
//	    return "http://localhost:4200";
//	    
//	}
	 
	
	 
	
}