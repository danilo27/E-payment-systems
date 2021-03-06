package iss.services;

import java.util.List;

import iss.model.Account;
import iss.model.Card;

public interface AccountService {
	Account findOne(Long id);
	List<Account> findAll();
	Account save(Account arg);
	Account findByPan(String pan);
	Account findByMerchantId(String merchantId);
}

