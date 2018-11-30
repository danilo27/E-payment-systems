package pc.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pc.model.PaymentRequest;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long>{

}
