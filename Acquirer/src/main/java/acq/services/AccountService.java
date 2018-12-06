package acq.services;

import java.util.List;

import acq.model.Account;
import acq.model.Card;

public interface AccountService {
	Account findOne(Long id);
	List<Account> findAll();
	Account save(Account arg);
	Account findByPan(String pan);
	Account findByMerchantId(String merchantId);
}

