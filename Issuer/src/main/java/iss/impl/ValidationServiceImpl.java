package iss.impl;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.services.AccountService;
import iss.services.MerchantService;
import iss.services.ValidationService;
import iss.model.Account;
import iss.model.Card;
import iss.model.Payment;
import iss.model.PaymentRequest;
import iss.model.enums.ReturnType;
 
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
		Account merchant = accService.findByMerchantId(pr.getMerchantId());
		Account buyer = accService.findByPan(c.getPan());
		 
		if(merchant != null && buyer != null){
			if(c.getCardHolderName().equals(accService.findByPan(c.getPan()).getCard().getCardHolderName())){
				if(c.getSecurityCode() == accService.findByPan(c.getPan()).getCard().getSecurityCode()){
					if(c.getExpiringDate().equals(accService.findByPan(c.getPan()).getCard().getExpiringDate())){
						if(Integer.parseInt(c.getExpiringDate().split("-")[1])>=Calendar.getInstance().get(Calendar.YEAR)){
							if(Integer.parseInt(c.getExpiringDate().split("-")[0])>=Calendar.getInstance().get(Calendar.MONTH)){
								if(buyer.getAccountBalance() - pr.getAmount() >= 0 ){
									buyer.setAccountBalance(buyer.getAccountBalance()-pr.getAmount());
									merchant.setAccountBalance(merchant.getAccountBalance()+pr.getAmount());
									accService.save(buyer);
									accService.save(merchant);
									
									return ReturnType.SUCCESS;
								}
							}
						}
					}
				}
			}
		}
		return ReturnType.ERROR;
	}
	
	@Override
	public ReturnType validateCardUserOnly(Payment pr, Card c) {	 
	 
		Account buyer = accService.findByPan(c.getPan());
		 
		if(buyer != null){
			if(c.getCardHolderName().equals(accService.findByPan(c.getPan()).getCard().getCardHolderName())){
				if(c.getSecurityCode() == accService.findByPan(c.getPan()).getCard().getSecurityCode()){
					if(c.getExpiringDate().equals(accService.findByPan(c.getPan()).getCard().getExpiringDate())){
						if(Integer.parseInt(c.getExpiringDate().split("-")[1])>=Calendar.getInstance().get(Calendar.YEAR)){
							if(Integer.parseInt(c.getExpiringDate().split("-")[0])>=Calendar.getInstance().get(Calendar.MONTH)){
								if(buyer.getAccountBalance() - pr.getAmount() >= 0 ){
									buyer.setAccountBalance(buyer.getAccountBalance()-pr.getAmount());					
									accService.save(buyer);
									
									return ReturnType.SUCCESS;
								}
							}
						}
					}
				}
			}
		}
		return ReturnType.ERROR;
	}

}
