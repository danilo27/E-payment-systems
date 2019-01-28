package iss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.repositories.AccountRepository;
import iss.services.AccountService;
import iss.model.Account;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository repo;
	
	@Override
	public Account findOne(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Account> findAll() {
		return repo.findAll();
	}

	@Override
	public Account save(Account arg) {
		return repo.save(arg);
	}

	@Override
	public Account findByPan(String pan) {
		return repo.findByCardPan(pan);
	}

	@Override
	public Account findByMerchantId(String merchantId) {
		return repo.findByMerchantId(merchantId);
	}

}
