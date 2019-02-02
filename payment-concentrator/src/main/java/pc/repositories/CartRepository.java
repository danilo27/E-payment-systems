package pc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pc.model.Cart; 

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	public Cart findByToken(String token);
}
