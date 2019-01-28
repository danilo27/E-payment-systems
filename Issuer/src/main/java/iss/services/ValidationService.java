package iss.services;

import iss.model.Card;
import iss.model.PaymentRequest;
import iss.model.enums.ReturnType;

public interface ValidationService {
	public ReturnType validatePaymentRequest(PaymentRequest pr);
	public ReturnType validateCard(PaymentRequest pr, Card c);
	public ReturnType validateCardUserOnly(PaymentRequest pr, Card c);
}
