package pc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;
import pc.dto.StringDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.PaymentRequest;
import pc.payments.impl.PayPalService;


@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {
	
	@Autowired
	private PayPalService payPalService;
	
	@RequestMapping(value = "/prepare",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> paypalRequest(@RequestBody PaymentRequest requestDto){
		String redirectUrl = payPalService.prepareTransaction(requestDto).getRedirectUrl();
		System.out.println(redirectUrl);
		return new ResponseEntity<>(new StringDto(redirectUrl), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> paypalConfirmation(@RequestBody PaymentConfirmationDto requestDto){
		String status = payPalService.proceedTransaction(requestDto).getSuccessMessage();
		return new ResponseEntity<>(new StringDto(status), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prepare/subscription",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> paypalSubscription(@RequestBody SubscriptionRequest requestDto){
		String redirectUrl = payPalService.prepareSubscription(requestDto).getRedirectUrl();
		return new ResponseEntity<>(new StringDto(redirectUrl), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm/subscription",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> paypalSubscriptionConfirmation(@RequestBody SubscriptionConfirmation requestDto){
		String status = payPalService.proceedSubscription(requestDto).getSuccessMessage();
		return new ResponseEntity<>(new StringDto(status), HttpStatus.OK);
	}

}
