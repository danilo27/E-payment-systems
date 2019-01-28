package iss.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iss.model.PaymentRequest;


@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long>{
	PaymentRequest findByToken(String token);
}
