package pc.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import pc.model.Merchant;
import pc.services.MerchantService;
 
public class Data {
	
	@Autowired
	private MerchantService merchantService;
	
	@PostConstruct
	private void init() {
		Merchant m1 = new Merchant("merchant1", "pas", "http://localhost:8081");
	}
}
