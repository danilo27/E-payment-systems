package acq.services;

import acq.model.Card;
import acq.model.Payment;
import acq.model.PaymentRequest;
import acq.model.enums.ReturnType;

public interface ValidationService {
	public ReturnType validatePaymentRequest(Payment p);
	public ReturnType validateCard(Payment p, Card c);
}
