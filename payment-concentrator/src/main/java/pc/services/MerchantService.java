package pc.services;

import java.util.List;

import pc.model.Merchant;
 
public interface MerchantService {
	List<Merchant> findAll();
 	Merchant findByMerchantId(String merchantId);
 	Merchant save(Merchant input);
}
