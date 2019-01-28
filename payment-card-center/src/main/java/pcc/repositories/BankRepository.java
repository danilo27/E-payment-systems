package pcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pcc.model.Bank;

@Repository
public interface BankRepository  extends JpaRepository<Bank, Long>{
	public Bank findByIin(String iin);
}
