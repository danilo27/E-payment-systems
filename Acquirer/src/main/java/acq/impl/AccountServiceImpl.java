package acq.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acq.model.Account;
import acq.repositories.AccountRepository;
import acq.services.AccountService;

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

}
