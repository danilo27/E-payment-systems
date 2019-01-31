package central.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import central.model.Article;
import central.model.Author;
import central.model.Issue;
import central.model.Magazine;
import central.model.MagazinePaymentType;
import central.repository.ArticleRepository;
import central.repository.AuthorRepository;
import central.repository.IssueRepository;
import central.repository.MagazineRepository;
import central.repository.SubscriptionRepository;
import central.repository.TransactionRepository;
 

@Component
public class Data {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
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
		authorRepository.deleteAll();
		
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
		 
		//i1.setFilepath("C:/issues/issue1.pdf");
		
		Issue i2 = new Issue();
		i2.setDate("15-01-2019");
		i2.setMagazine(m2);
		i2.setPrice(null);
		//i2.setFilepath("C:/issues/issue2.pdf");
		
		issueRepository.save(i1);
		issueRepository.save(i2);
		
		System.out.println("i1: " + i1.toString());
		
		m1.getIssues().add(i1);
		m2.getIssues().add(i2);
		
		magazineRepository.save(m1);
		magazineRepository.save(m2);
		
		Author author1 = new Author();
		author1.setfName("Petar");
		author1.setlName("Peric");
		author1 = authorRepository.save(author1);
		
		Article a1 = new Article();
		a1.setName("C++ Programming");
		a1.setAuthor(author1);
		a1.setIssue(i1);
		a1.setPrice(Double.parseDouble("5.0"));
		a1.setFilepath("C:/articles/article1.pdf");
		
		Article a2 = new Article();
		a2.setName("Introduction to Functional programming");
		a2.setAuthor(author1);
		a2.setIssue(i1);
		a2.setPrice(Double.parseDouble("5.0"));
		a2.setFilepath("C:/articles/article2.pdf");
		
		
		Article a3 = new Article();
		a3.setName("IOS and Android programming");
		a3.setAuthor(author1);
		a3.setIssue(i1);
		a3.setPrice(Double.parseDouble("5.0"));
		
		a1 = articleRepository.save(a1);
		a2 = articleRepository.save(a2);
		a3 = articleRepository.save(a3);
		
		i1.getArticles().add(a1);
		i1.getArticles().add(a2);
		i1.getArticles().add(a3);
		
		issueRepository.save(i1);
 
		
	}

}
