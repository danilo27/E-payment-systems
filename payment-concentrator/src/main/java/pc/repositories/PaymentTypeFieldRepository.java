package pc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pc.model.PaymentTypeField;

@Repository
public interface PaymentTypeFieldRepository extends JpaRepository<PaymentTypeField, Long>{

}
