package iss.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import iss.model.Account;
import iss.model.Card;
import iss.services.AccountService;
import iss.services.CardService;
import iss.services.MerchantService;
import iss.model.Merchant;
@Component
public class Data {
	
	@Autowired
	private AccountService accService;
	
	@Autowired 
	private CardService cardService;
	
	@Autowired
	private MerchantService merchantService;
	
	@PostConstruct
	private void init() throws ParseException {
		String oldstring = "11-2019";
											
		Card c1 = new Card("3333333333333333", 333, "John Wick", oldstring);
		cardService.save(c1);
								   
		Account a1 = new Account("3333333333333333", 5000.0, c1);
		//a1.setMerchantId("johnMerchant");
		accService.save(a1);
		
		//Merchant m1 = new Merchant("johnMerchant", "pas", "http://localhost:8083");
		//merchantService.save(m1);
	}
}
