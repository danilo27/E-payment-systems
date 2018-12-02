package pc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pc.model.Merchant;
import pc.repositories.MerchantRepository;
import pc.services.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;
 
	@Override
	public List<Merchant> findAll() {
		return merchantRepository.findAll();
	}

	@Override
	public Merchant findByMerchantId(String merchantId) {
		return merchantRepository.findByMerchantId(merchantId);
	}

	@Override
	public Merchant save(Merchant input) {
		return merchantRepository.save(input);
	}

}
