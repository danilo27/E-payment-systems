package pc.controllers;

import java.net.URI;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import pc.repositories.CartRepository;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	private IPaymentExtensionPointFactory factory;

	@Autowired
	private CartRepository cartRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@RequestMapping(value = "/prepare/{paymentType}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> paymentRequest(@RequestBody Cart cart, @PathVariable String paymentType)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, NotFoundException {
		
		logger.info("PAYMENT: payment type: " + paymentType + " merchant order id: " + cart.getMerchantOrderId() + " merchant id: " + cart.getMerchantId());
		cartRepository.save(cart);
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		return payment.prepareTransaction(cart);
	}

	@RequestMapping(value = "/confirm/{paymentType}", method = RequestMethod.GET)
	public ResponseEntity<TransactionResult> paymentConfirmation(@PathVariable String paymentType,
			@PathParam(value = "paymentId") String paymentId, @PathParam(value = "token") String token,
			@PathParam(value = "PayerID") String PayerID)
			throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	
		logger.info("PAYMENT: payment id: " + paymentId + " Payer ID: " + PayerID + " payment type: " + paymentType);
		PaymentConfirmationDto pcd = new PaymentConfirmationDto(paymentId, PayerID);
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		TransactionResult tr = payment.proceedTransaction(pcd);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(tr.getRedirectUrl()));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

	@RequestMapping(value = "/prepare/subscription/{paymentType}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResult> paymentSubscription(@RequestBody SubscriptionRequest requestDto,
			@PathVariable String paymentType)
			throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		logger.info("SUBSCRIPTION: payment type: " + paymentType);
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		return new ResponseEntity<>(payment.prepareSubscription(requestDto), HttpStatus.OK);
	}

	@RequestMapping(value = "/confirm/subscription/{paymentType}", method = RequestMethod.GET)
	public ResponseEntity<TransactionResult> paymentSubscriptionConfirmation(
			@PathVariable String paymentType, @PathParam("token") String token)
			throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		logger.info("SUBSCRIPTION PROCEED payment type: " + paymentType);
		SubscriptionConfirmation requestDto = new SubscriptionConfirmation(token);
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		TransactionResult tr = payment.proceedSubscription(requestDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(tr.getRedirectUrl()));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	@RequestMapping(value = "/cancel/{paymentType}/{cartId}", method = RequestMethod.GET)
	public ResponseEntity<StringDto> cancelRequest(@PathVariable String paymentType, @PathVariable Long cartId, @PathParam(value = "token") String token) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NotFoundException {
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		StringDto response = payment.cancelTransaction(cartId);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(response.getValue()));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	@RequestMapping(value = "/error/{paymentType}/{cartId}", method = RequestMethod.GET)
	public ResponseEntity<StringDto> errorRequest(@PathVariable String paymentType, @PathVariable Long cartId, @PathParam(value = "token") String token) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NotFoundException {
		IPaymentExtensionPoint payment = factory.getPaymentMethod(paymentType);
		StringDto response = payment.errorTransaction(cartId);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(response.getValue()));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
}
