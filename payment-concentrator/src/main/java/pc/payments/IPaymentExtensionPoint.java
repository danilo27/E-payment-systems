package pc.payments;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;

public interface IPaymentExtensionPoint {
	
	TransactionResult prepareTransaction(PaymentRequest req);
	TransactionResult proceedTransaction(PaymentConfirmationDto req);
	TransactionResult prepareSubscription(SubscriptionRequest req);
	TransactionResult proceedSubscription(SubscriptionConfirmation req);
}