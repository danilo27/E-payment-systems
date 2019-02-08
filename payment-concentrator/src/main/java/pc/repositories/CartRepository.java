package pc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pc.model.Cart; 

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	public Cart findByToken(String token);
	public Cart findByMerchantOrderId(Long merchantOrderId);
	public Cart findByPaymentId(String paymentId);
	public List<Cart> findByStatus(String string);
}
