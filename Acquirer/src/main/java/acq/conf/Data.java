package acq.conf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import acq.model.Account;
import acq.model.Card;
import acq.services.AccountService;
import acq.services.CardService;
import acq.services.MerchantService;
import acq.model.Merchant;
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
		Date date = new SimpleDateFormat("MM-yyyy").parse(oldstring);
		
		Card c1 = new Card("1111111111111111", 111, "Danilo Bujisa", oldstring);
		cardService.save(c1);
		
		Account a1 = new Account("1111111111111111", 5000.0, c1);
		a1.setMerchantId("12345678");
		accService.save(a1);
		
		System.out.println(cardService.findAll());
		System.out.println(accService.findAll());
		
		Merchant m1 = new Merchant("12345678", "pas", "http://localhost:8081");
		merchantService.save(m1);
	}
}
