package central.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Cart;
import central.model.Magazine;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	Cart findOneByToken(String token);
	public Cart findByMerchantOrderId(Long merchantOrderId);
	public List<Cart> findByStatus(String string);
}
