package iss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.repositories.MerchantRepository;
import iss.services.MerchantService;
import iss.model.Merchant;
@Service
public class MerchantServiceImpl implements MerchantService {
	
	@Autowired
	MerchantRepository repo;
	
	@Override
	public Merchant findOne(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Merchant> findAll() {
		return repo.findAll();
	}

	@Override
	public Merchant findByMerchantId(String merchantId) {
		return repo.findByMerchantId(merchantId);
	}

	@Override
	public Merchant save(Merchant arg) {
		return repo.save(arg);
	}

}
