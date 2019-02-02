package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Cart;
import central.model.Magazine;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	Cart findOneByToken(String token);
}
