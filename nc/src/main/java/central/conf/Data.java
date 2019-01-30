package central.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import central.model.Issue;
import central.model.Magazine;
import central.model.MagazinePaymentType;
import central.repository.ArticleRepository;
import central.repository.IssueRepository;
import central.repository.MagazineRepository;
import central.repository.SubscriptionRepository;
import central.repository.TransactionRepository;
 

@Component
public class Data {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@PostConstruct
	private void init() {
		magazineRepository.deleteAll();
		issueRepository.deleteAll();
		articleRepository.deleteAll();
		subscriptionRepository.deleteAll();
		
		Magazine m1 = new Magazine();
		Magazine m2 = new Magazine();
		
		m1.setIssn("12345678");
		m1.setPaymentType(MagazinePaymentType.PAID_ACCESS);
		m1.setName("Computer Science Magazine");
		 
		m2.setIssn("87654321");
		m2.setPaymentType(MagazinePaymentType.OPEN_ACCESS);
		m2.setName("Biology Magazine");
		m2.setMembershipPrice(Double.parseDouble("35.0"));
		
		
		m1 = magazineRepository.save(m1);
		m2 = magazineRepository.save(m2);
		
		Issue i1 = new Issue();
		i1.setDate("01-01-2019");
		i1.setMagazine(m1);
		i1.setPrice(Double.parseDouble("15.0"));
		
		Issue i2 = new Issue();
		i2.setDate("15-01-2019");
		i2.setMagazine(m2);
		i2.setPrice(null);
		
		issueRepository.save(i1);
		issueRepository.save(i2);

		m1.getIssues().add(i1);
		m2.getIssues().add(i2);
		
		magazineRepository.save(m1);
		magazineRepository.save(m2);
		
	}

}
