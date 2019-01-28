package pcc.conf;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pcc.model.Bank;
import pcc.repositories.BankRepository;


@Component
public class PccData {
	@Autowired 
	BankRepository bankRepo;
	
	@PostConstruct
	private void init() {
		Bank b1 = new Bank("111111", "http://localhost:8081"); //acquirer
		bankRepo.save(b1);
		Bank b2 = new Bank("333333", "http://localhost:8083"); //issuer
		bankRepo.save(b2);
	}
}
