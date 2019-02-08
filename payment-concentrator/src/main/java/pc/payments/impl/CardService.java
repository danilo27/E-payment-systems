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
import pc.model.PaymentType;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;
import pc.repositories.CartRepository;
import pc.repositories.MerchantInfoRepository;
import pc.repositories.MerchantRepository;
import pc.services.PaymentRequestService;

@Service
public class CardService implements IPaymentExtensionPoint{
	
	@Autowired
	private PaymentRequestService paymentRequestService;
	
	@Autowired
	private MerchantInfoRepository merchantInfoRepository;
	
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
	public ResponseEntity<StringDto> prepareTransaction(Cart cart) {  
 
		if(cart!=null){
			//Merchant merchant = merchantRepository.findByMerchantId(cart.getMerchantId());
//<<<<<<< HEAD:payment-concentrator/src/main/java/pc/payments/card/CardService.java
			//req.setId(null);
			
			String merchantBankId = merchantInfoRepository.findMerchantData("Card", cart.getMerchantId(), "merchantId").getValue();
			String merchantBankPass = merchantInfoRepository.findMerchantData("Card", cart.getMerchantId(), "merchantPassword").getValue();
			String bankUrl = merchantInfoRepository.findMerchantData("Card", cart.getMerchantId(), "merchantBankUrl").getValue();
		//	req.setMerchantId(merchantBankId);
//=======
			/*req.setId(null);
			req.setMerchantId(cart.getMerchantId());
>>>>>>> 0c5dc2f1c2e6ae9c0ee9d2b8e5544a473118b607:payment-concentrator/src/main/java/pc/payments/impl/CardService.java
			req.setMerchantOrderId(cart.getMerchantOrderId());
			req.setMerchantPassword(merchantBankPass);
			req.setMerchantTimestamp(cart.getMerchantTimestamp());
			req.setAmount(cart.getTotalPrice());
			req = paymentRequestService.save(req);
<<<<<<< HEAD:payment-concentrator/src/main/java/pc/payments/card/CardService.java
			System.out.println("PaymentRequest saved: " + req.toString());
			 
			 
			
			String fooResourceUrl = bankUrl+"/acqBank/getUrlAndId";
			ResponseEntity<Payment> response = restTemplate().postForEntity(fooResourceUrl, req, Payment.class);
=======
			System.out.println("PaymentRequest saved: " + req.toString());*/
			
			
			
			PaymentRequest pr = new PaymentRequest();
			pr.setAmount(cart.getTotalPrice());
			//pr.setMerchantId(cart.getMerchantId());
			pr.setMerchantOrderId(cart.getMerchantOrderId());
			pr.setMerchantTimestamp(cart.getMerchantTimestamp());
			pr.setMerchantId(merchantBankId);
			pr.setMerchantPassword(merchantBankPass);
			 
			pr = paymentRequestService.save(pr);
			
			System.out.println("Pr before sending: " + pr.toString());
			
			
			
			
			String fooResourceUrl = bankUrl+"/acqBank/getUrlAndId";
			ResponseEntity<Payment> response =  restTemplate().postForEntity(fooResourceUrl, pr, Payment.class);
//>>>>>>> 0c5dc2f1c2e6ae9c0ee9d2b8e5544a473118b607:payment-concentrator/src/main/java/pc/payments/impl/CardService.java
			PaymentConfirmationDto dto = new PaymentConfirmationDto();
			StringDto result = new StringDto("");
			result.setValue(response.getBody().getPaymentUrl());
			System.out.println("url: " + response.getBody().getPaymentUrl());
			dto.setResponse(response);
			return new ResponseEntity<> (new StringDto(response.getBody().getPaymentUrl()), HttpStatus.OK);		//	SKONTATI
		}
 
		 return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TransactionResult proceedTransaction(PaymentConfirmationDto req) {
		//ResponseEntity<Boolean> res = restTemplate().postForEntity(pcUrl+"/api/pc/returnToPc", cart, Boolean.class);
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

	@Override
	public StringDto cancelTransaction(Long cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringDto errorTransaction(Long cartId) {
		// TODO Auto-generated method stub
		return null;
	}


}
