package pc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import pc.dto.PaymentConfirmationDto;
import pc.dto.StringDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.Cart;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;
import pc.payments.IPaymentExtensionPointFactory;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
	private IPaymentExtensionPointFactory factory;
	
	@RequestMapping(value = "/prepare/{paymentType}",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> paypalRequest(@RequestBody Cart cart, @PathVariable String paymentType) throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		return payment.prepareTransaction(cart);
	}
	
	@RequestMapping(value = "/confirm",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResult> paypalConfirmation(@RequestBody PaymentConfirmationDto requestDto) throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		IPaymentExtensionPoint paymentType = factory.getPaymentMethod(requestDto.getPaymentTypeName());
		TransactionResult tr = paymentType.proceedTransaction(requestDto);
		return new ResponseEntity<>(tr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prepare/subscription",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResult> paypalSubscription(@RequestBody SubscriptionRequest requestDto) throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		IPaymentExtensionPoint paymentType = factory.getPaymentMethod(requestDto.getPaymentTypeName());
		TransactionResult tr = paymentType.prepareSubscription(requestDto);
		return new ResponseEntity<>(tr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm/subscription",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResult> paypalSubscriptionConfirmation(@RequestBody SubscriptionConfirmation requestDto) throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		IPaymentExtensionPoint paymentType = factory.getPaymentMethod(requestDto.getPaymentTypeName());
		TransactionResult tr = paymentType.proceedSubscription(requestDto);
		return new ResponseEntity<>(tr, HttpStatus.OK);
	}
}
