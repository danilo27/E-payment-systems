package acq.services;

import java.util.List;

import org.springframework.stereotype.Service;

import acq.model.Merchant;

public interface MerchantService {
	Merchant findOne(Long id);
	List<Merchant> findAll();
 	Merchant findByMerchantId(String merchantId);
 	Merchant save(Merchant arg);
}
