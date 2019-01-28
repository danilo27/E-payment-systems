package iss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iss.model.Account;

 
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByCardPan(String pan);
	Account findByMerchantId(String merchantId);

}
