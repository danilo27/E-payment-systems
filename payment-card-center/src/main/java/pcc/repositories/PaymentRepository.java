package pcc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pcc.model.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	public List<Payment> findByMessage(String string);
}
