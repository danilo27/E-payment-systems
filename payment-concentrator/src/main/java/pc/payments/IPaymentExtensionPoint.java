package pc.payments;

import org.springframework.http.ResponseEntity;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;
import pc.dto.StringDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.Cart;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;

public interface IPaymentExtensionPoint {
	
	ResponseEntity<StringDto> prepareTransaction(Cart req);
	TransactionResult proceedTransaction(PaymentConfirmationDto req);
	TransactionResult prepareSubscription(SubscriptionRequest req);
	TransactionResult proceedSubscription(SubscriptionConfirmation req);
}
