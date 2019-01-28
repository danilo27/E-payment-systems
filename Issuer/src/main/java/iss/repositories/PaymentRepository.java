package iss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iss.model.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
