package pc.services;

import java.util.List;
import java.util.Optional;

import pc.model.PaymentRequest;

 

public interface PaymentRequestService {
	Optional<PaymentRequest> findOne(Long id);
 	List<PaymentRequest> findAll();
  
 	PaymentRequest save(PaymentRequest arg);
}
