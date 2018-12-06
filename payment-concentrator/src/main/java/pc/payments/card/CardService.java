package pc.payments.card;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;
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
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		req.setMerchantTimestamp(Calendar.getInstance().getTime());
		PaymentRequest request = paymentRequestService.save(req);
		String fooResourceUrl = bankAcquirer+"/acqBank/getUrlAndId";
		ResponseEntity<URL_ID_DTO> response = restTemplate().postForEntity(fooResourceUrl, request, URL_ID_DTO.class);
		TransactionResult result = new TransactionResult();
		result.setResponse(response);
		return result;
	}

	@Override
	public TransactionResult proceedTransaction(PaymentConfirmationDto req) {
		return new TransactionResult();
	}

}
