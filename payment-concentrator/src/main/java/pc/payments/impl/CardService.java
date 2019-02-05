package pc.payments.impl;

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
import pc.dto.StringDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.Cart;
import pc.model.Merchant;
import pc.model.Payment;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;
import pc.repositories.CartRepository;
import pc.repositories.MerchantRepository;
import pc.services.PaymentRequestService;

@Service
public class CardService implements IPaymentExtensionPoint{
	
	@Autowired
	private PaymentRequestService paymentRequestService;
	
	@Value("${bank.acquirer}")
	private String bankAcquirer;
	
	@Value("${bank.issuer}")
	private String bankIssuer;
	
	@Autowired 
	MerchantRepository merchantRepository;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public ResponseEntity<StringDto> prepareTransaction(Cart cart) { //req.getId() == id cart-a u PC-u
		/*System.out.println("req: " + req.toString());
		//System.out.println(cartRepository.findAll().toString());
		for(Cart c : cartRepository.findAll()){
			System.out.println(c.getId() + " - " + c.getMerchantOrderId());
		}
		Cart cart = cartRepository.findById(req.getId()).orElse(null);*/
		
		//TODO prikaziti dozvoljene nacine placanja (cart -> merchantId)
		if(cart!=null){
			Merchant merchant = merchantRepository.findByMerchantId(cart.getMerchantId());
			/*req.setId(null);
			req.setMerchantId(cart.getMerchantId());
			req.setMerchantOrderId(cart.getMerchantOrderId());
			req.setMerchantPassword(cart.getMerchantPassword());
			req.setMerchantTimestamp(cart.getMerchantTimestamp());
			req.setAmount(cart.getTotalPrice());
			req = paymentRequestService.save(req);
			System.out.println("PaymentRequest saved: " + req.toString());*/
			String fooResourceUrl = merchant.getMerchantBankUrl()+"/acqBank/getUrlAndId";
			ResponseEntity<Payment> response = restTemplate().postForEntity(fooResourceUrl, cart, Payment.class);
			PaymentConfirmationDto dto = new PaymentConfirmationDto();
			dto.setResponse(response);
			return new ResponseEntity<> (new StringDto(proceedTransaction(dto).getRedirectUrl()), HttpStatus.OK);		//	SKONTATI
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
