package pc.payments;

import pc.dto.PaymentRequestDto;

public interface IPaymentExtensionPoint {
	
	String prepareTransaction(PaymentRequestDto req);
	String proceedTransaction(PaymentRequestDto req);
}
