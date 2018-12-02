package pc.payments;

import pc.model.PaymentRequest;

public interface IPaymentExtensionPoint {
	String prepareTransaction(PaymentRequest req);
	String proceedTransaction(PaymentRequest req);
}
