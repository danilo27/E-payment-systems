package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import central.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

}
