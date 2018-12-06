package acq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acq.model.Account;

 
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByCardPan(String pan);
	Account findByMerchantId(String merchantId);

}
