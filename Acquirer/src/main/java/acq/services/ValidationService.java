package acq.services;

import acq.model.Card;
import acq.model.PaymentRequest;

public interface ValidationService {
	public String validate(PaymentRequest pr, Card c);
}
