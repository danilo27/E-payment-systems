package pc.payments;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;

public interface IPaymentExtensionPoint {
	
	TransactionResult prepareTransaction(PaymentRequest req);
	TransactionResult proceedTransaction(PaymentConfirmationDto req);
}
