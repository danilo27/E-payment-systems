package pc.payments;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;

public interface IPaymentExtensionPoint {
	
	String prepareTransaction(PaymentRequestDto req);
	String proceedTransaction(PaymentConfirmationDto req);
}
