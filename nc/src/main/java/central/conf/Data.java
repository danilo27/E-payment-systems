package central.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 

@Component
public class Data {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@PostConstruct
	private void init() {
		
	}

}
