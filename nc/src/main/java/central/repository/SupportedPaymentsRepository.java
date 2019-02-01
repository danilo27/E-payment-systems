package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import central.model.SupportedPayments;

public interface SupportedPaymentsRepository extends JpaRepository<SupportedPayments, Long> {

}
