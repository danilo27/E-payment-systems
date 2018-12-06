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
import pc.model.Payment;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;
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

	@Override
	public TransactionResult prepareTransaction(PaymentRequest req) {
		req.setMerchantTimestamp(Calendar.getInstance().getTime());
		PaymentRequest request = paymentRequestService.save(req);
		String fooResourceUrl = bankAcquirer+"/acqBank/getUrlAndId";
		ResponseEntity<Payment> response = restTemplate().postForEntity(fooResourceUrl, request, Payment.class);
		PaymentConfirmationDto dto = new PaymentConfirmationDto();
		dto.setResponse(response);
		return proceedTransaction(dto);
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

}
