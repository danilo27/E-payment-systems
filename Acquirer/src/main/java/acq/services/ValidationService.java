package acq.services;

import acq.model.Card;
import acq.model.PaymentRequest;
import acq.model.enums.ReturnType;

public interface ValidationService {
	public ReturnType validatePaymentRequest(PaymentRequest pr);
	public ReturnType validateCard(PaymentRequest pr, Card c);
}
