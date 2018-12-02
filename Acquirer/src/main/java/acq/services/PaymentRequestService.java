package acq.services;

import java.util.List;
import java.util.Optional;

import acq.model.PaymentRequest;

 

public interface PaymentRequestService {
	Optional<PaymentRequest> findOne(Long id);
 	List<PaymentRequest> findAll();
  
 	PaymentRequest save(PaymentRequest arg);
}
