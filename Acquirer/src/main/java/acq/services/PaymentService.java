package acq.services;

import java.util.List;
import java.util.Optional;

import acq.model.Payment;
import acq.model.PaymentRequest;

public interface PaymentService {
	Optional<Payment> findOne(Long id);
 	List<Payment> findAll();
 	Payment save(Payment arg);
 	public List<Payment> findByMessage(String string);
}
