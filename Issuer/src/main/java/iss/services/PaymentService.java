package iss.services;

import java.util.List;
import java.util.Optional;

import iss.model.Payment;
import iss.model.PaymentRequest;

public interface PaymentService {
	Optional<Payment> findOne(Long id);
 	List<Payment> findAll();
 	Payment save(Payment arg);
  
}
