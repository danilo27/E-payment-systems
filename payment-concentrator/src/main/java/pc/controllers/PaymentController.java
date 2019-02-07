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
	
	@RequestMapping(value = "/confirm/{paymentType}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResult> paypalConfirmation(@RequestBody PaymentConfirmationDto requestDto, @PathVariable String paymentType) throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		return new ResponseEntity<>(payment.proceedTransaction(requestDto), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prepare/subscription/{paymentType}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResult> paypalSubscription(@RequestBody SubscriptionRequest requestDto, @PathVariable String paymentType) throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		return new ResponseEntity<>(payment.prepareSubscription(requestDto), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm/subscription/{paymentType}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResult> paypalSubscriptionConfirmation(@RequestBody SubscriptionConfirmation requestDto, @PathVariable String paymentType) throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		return new ResponseEntity<>(payment.proceedSubscription(requestDto), HttpStatus.OK);
	}
}
