package pc.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pc.model.Merchant;
import pc.services.MerchantService;

@Component
public class Data {
	
	@Autowired
	private MerchantService merchantService;
	
	@PostConstruct
	private void init() {
		Merchant m1 = new Merchant("merchant1", "pas", "http://localhost:8081");
	}
}
