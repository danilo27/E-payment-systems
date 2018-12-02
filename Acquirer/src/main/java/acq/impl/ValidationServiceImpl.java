package acq.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acq.model.Account;
import acq.model.Card;
import acq.model.PaymentRequest;
import acq.services.AccountService;
import acq.services.ValidationService;
 
@Transactional
@Service
public class ValidationServiceImpl implements ValidationService{
	
	@Autowired
	private AccountService accService;
	
	@Override
	public String validate(PaymentRequest pr, Card c) {
		//TODO validate
		return "SUCCESS";
	}

}
