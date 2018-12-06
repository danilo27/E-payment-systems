package acq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acq.model.Merchant;
 
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>{
	Merchant findByMerchantId(String merchantId);
}
