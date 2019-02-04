package pc.controllers;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pc.dto.PaymentConfirmationDto;
import pc.dto.StringDto;
import pc.model.Cart;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.card.CardService;

@RestController
@RequestMapping("/card")
public class CardPaymentController {

	@Autowired
	private CardService cardService;


	@PostMapping("/prepare")
	public ResponseEntity<?> preparePayment(@RequestBody Cart cart) throws URISyntaxException {

		return new ResponseEntity<> (new StringDto(""), HttpStatus.OK);
		//return cardService.prepareTransaction(cart).getResponse();

	}

	@PostMapping("/proceed")
	public TransactionResult proceedPayment(@RequestBody PaymentConfirmationDto request) throws URISyntaxException {

		return cardService.proceedTransaction(request);
	}

}
