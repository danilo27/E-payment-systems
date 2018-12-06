package acq.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acq.model.Account;
import acq.model.Card;
import acq.model.PaymentRequest;
import acq.model.enums.ReturnType;
import acq.services.AccountService;
import acq.services.MerchantService;
import acq.services.ValidationService;
 
@Transactional
@Service
public class ValidationServiceImpl implements ValidationService{
	
	@Autowired
	private AccountService accService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Override
	public ReturnType validatePaymentRequest(PaymentRequest pr) {
		 if(merchantService.findByMerchantId(pr.getMerchantId())!=null){
			 if(merchantService.findByMerchantId(pr.getMerchantId()).getMerchantPass().equals(pr.getMerchantPassword())){
				return ReturnType.SUCCESS;
			 }
		 }
		 return null;
	}

	@Override
	public ReturnType validateCard(PaymentRequest pr, Card c) {
		if(accService.findByMerchantId(pr.getMerchantId())!=null && accService.findByPan(c.getPan())!=null){
			if(c.getCardHolderName().equals(accService.findByPan(c.getPan()).getCard().getCardHolderName())){
				if(c.getSecurityCode() != accService.findByPan(c.getPan()).getCard().getSecurityCode()){
					if(c.getExpiringDate().equals(accService.findByPan(c.getPan()).getCard().getExpiringDate())){
						
					}
				}
			}
		}
		return ReturnType.SUCCESS;
	}

}
