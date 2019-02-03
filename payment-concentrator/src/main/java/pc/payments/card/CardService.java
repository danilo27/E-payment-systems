package pc.payments.card;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.Cart;
import pc.model.Payment;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;
import pc.repositories.CartRepository;
import pc.services.PaymentRequestService;

@Service
public class CardService implements IPaymentExtensionPoint{
	
	@Autowired
	private PaymentRequestService paymentRequestService;
	
	@Value("${bank.acquirer}")
	private String bankAcquirer;
	
	@Value("${bank.issuer}")
	private String bankIssuer;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public TransactionResult prepareTransaction(PaymentRequest req) {
		System.out.println("req: " + req.getId());
		System.out.println(cartRepository.findAll().toString());
		Cart cart = cartRepository.findByMerchantOrderId(req.getId());
		//TODO prikaziti dozvoljene nacine placanja (cart -> merchantId)
		if(cart!=null){
			req.setId(null);
			req.setMerchantId(cart.getMerchantId());
			req.setMerchantOrderId(cart.getMerchantOrderId());
			req.setMerchantPassword(cart.getMerchantPassword());
			req.setMerchantTimestamp(cart.getMerchantTimestamp());
			req.setAmount(cart.getTotalPrice());
			req = paymentRequestService.save(req);
			System.out.println("req obj: " + req.toString());
			String fooResourceUrl = bankAcquirer+"/acqBank/getUrlAndId";
			ResponseEntity<Payment> response = restTemplate().postForEntity(fooResourceUrl, req, Payment.class);
			PaymentConfirmationDto dto = new PaymentConfirmationDto();
			dto.setResponse(response);
			return proceedTransaction(dto);
		}
 
		 return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TransactionResult proceedTransaction(PaymentConfirmationDto req) {
		TransactionResult result = new TransactionResult();
		if(((ResponseEntity<Payment>)req.getResponse()).getBody().getMessage().equals("")){
			System.out.println(((ResponseEntity<Payment>)req.getResponse()).getBody().toString());
			result.setResponse(((ResponseEntity<Payment>)req.getResponse()));
			result.setSuccessMessage("SUCCESS");
		} else {
			result.setResponse(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error"));
		}
		return result;
	}



	@Override
	public TransactionResult prepareSubscription(SubscriptionRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResult proceedSubscription(SubscriptionConfirmation req) {
		// TODO Auto-generated method stub
		return null;
	}


}
