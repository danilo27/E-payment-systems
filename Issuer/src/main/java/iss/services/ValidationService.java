package iss.services;

import iss.model.Card;
import iss.model.Payment;
import iss.model.PaymentRequest;
import iss.model.enums.ReturnType;

public interface ValidationService {
	public ReturnType validatePaymentRequest(PaymentRequest pr);
	public ReturnType validateCard(PaymentRequest pr, Card c);
	public ReturnType validateCardUserOnly(Payment pr, Card c);
}
