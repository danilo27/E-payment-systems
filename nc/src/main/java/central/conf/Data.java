package central.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import central.model.Administrator;
import central.model.Article;
import central.model.Author;
import central.model.Issue;
import central.model.Magazine;
import central.model.MagazinePaymentType;
import central.model.Merchant;
import central.model.Role;
import central.model.RoleName;
import central.model.SupportedPayments;
import central.model.User;
import central.repository.AdministratorRepository;
import central.repository.ArticleRepository;
import central.repository.AuthorRepository;
import central.repository.IssueRepository;
import central.repository.MagazineRepository;
import central.repository.MerchantRepository;
import central.repository.RoleRepository;
import central.repository.SubscriptionRepository;
import central.repository.SupportedPaymentsRepository;
import central.repository.TransactionRepository;
import central.repository.UserRepository;
 

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
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdministratorRepository adminRepository;
	
	@Autowired
	private SupportedPaymentsRepository supportedPaymentsRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@PostConstruct
	private void init() {
		
		magazineRepository.deleteAll();
		issueRepository.deleteAll();
		articleRepository.deleteAll();
		subscriptionRepository.deleteAll();
		authorRepository.deleteAll();
		userRepository.deleteAll();
		roleRepository.deleteAll();
		adminRepository.deleteAll();
		supportedPaymentsRepository.deleteAll();
		merchantRepository.deleteAll();
		
		Role r1 = new Role();
		r1.setName(RoleName.USER);
		Role r2 = new Role();
		r2.setName(RoleName.AUTHOR);
		Role r3 = new Role();
		r3.setName(RoleName.ADMINISTRATOR);

		roleRepository.save(r1);
		roleRepository.save(r2);
		roleRepository.save(r3);
		
		Magazine m1 = new Magazine();
		Magazine m2 = new Magazine();
		Magazine m3 = new Magazine();
		
		m1.setIssn("12345678");
		m1.setPaymentType(MagazinePaymentType.PAID_ACCESS);
		m1.setMembershipPrice(Double.parseDouble("4.0"));
		m1.setName("Computer Science Magazine");
		
		
		m2.setIssn("87654321");
		m2.setPaymentType(MagazinePaymentType.OPEN_ACCESS);
		m2.setName("Biology Magazine");
		m2.setMembershipPrice(Double.parseDouble("4.0"));
		
		m3.setIssn("12344321");
		m3.setPaymentType(MagazinePaymentType.PAID_ACCESS);
		m3.setMembershipPrice(Double.parseDouble("3.0"));
		m3.setName("Architecture Magazine");
		 
		
		m1 = magazineRepository.save(m1);
		m2 = magazineRepository.save(m2);
		m3 = magazineRepository.save(m3);
		
		Merchant daniloMerchant = new Merchant();
		daniloMerchant.setMerchantId("12345678");
		merchantRepository.save(daniloMerchant);
		
		m1.setMerchant(daniloMerchant);
		
		Merchant drugiMerchant = new Merchant();
		drugiMerchant.setMerchantId("87654321");
		merchantRepository.save(drugiMerchant);
		
		m2.setMerchant(drugiMerchant);
		
		magazineRepository.save(m1);
		magazineRepository.save(m2);
		
		daniloMerchant.setMagazine(m1);
		drugiMerchant.setMagazine(m2);
		

		Issue i1 = new Issue();
		i1.setDate("01-01-2019");
		i1.setMagazine(m1);
		i1.setPrice(Double.parseDouble("15.0"));
		i1.setFilepath("C:/issues/"+i1.getMagazine().getIssn()+"_"+i1.getDate()+".pdf");
		
		Issue i2 = new Issue();
		i2.setDate("15-01-2019");
		i2.setMagazine(m2);
		i2.setPrice(null);
		//i2.setFilepath("C:/issues/issue2.pdf");
		
		Issue i3 = new Issue();
		i3.setDate("15-01-2019");
		i3.setMagazine(m3);
		i3.setPrice(Double.parseDouble("15.0"));
		i3.setFilepath("C:/issues/"+i3.getMagazine().getIssn()+"_"+i3.getDate()+".pdf");
		
		issueRepository.save(i1);
		issueRepository.save(i2);
		issueRepository.save(i3);
		
		
		m1.getIssues().add(i1);
		m2.getIssues().add(i2);
		m3.getIssues().add(i3);
		
		magazineRepository.save(m1);
		magazineRepository.save(m2);
		magazineRepository.save(m3);
		
		Author author1 = new Author();
		author1.setFirstName("Petar");
		author1.setLastName("Peric");
		author1.setUsername("petar");
		author1.setPassword("$2a$10$L49EYcQMFQQOnOMxDkIv0.QwQNo1SYqMHKxY1iDDPxX8OF5Ovhnbu");
		author1.setRole(r2);
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
		a3.setFilepath("C:/articles/article3.pdf");
		
		Article a4 = new Article();
		a4.setName("Rome Architecture");
		a4.setAuthor(author1);
		a4.setIssue(i3);
		a4.setPrice(Double.parseDouble("5.0"));
		a4.setFilepath("C:/articles/article4.pdf");
		
		Article a5 = new Article();
		a5.setName("Biology of Wild Animals");
		a5.setAuthor(author1);
		a5.setIssue(i2);
		a5.setPrice(null);
		a5.setFilepath("C:/articles/article4.pdf");
		
		a1 = articleRepository.save(a1);
		a2 = articleRepository.save(a2);
		a3 = articleRepository.save(a3);
		a4 = articleRepository.save(a4);
		a5 = articleRepository.save(a5);
		
		i1.getArticles().add(a1);
		i1.getArticles().add(a2);
		i1.getArticles().add(a3);	
		issueRepository.save(i1);
			
		i3.getArticles().add(a4);	
		issueRepository.save(i3);
			
		i2.getArticles().add(a5);
		issueRepository.save(i2);
		
		
		
		
		
		
		User u1 = new User();
		u1.setFirstName("Milorad");
		u1.setLastName("Miloradic");
		u1.setUsername("pera");
		u1.setPassword("$2a$10$L49EYcQMFQQOnOMxDkIv0.QwQNo1SYqMHKxY1iDDPxX8OF5Ovhnbu");
		u1.setRole(r1);
		
		User u2 = new User();
		u2.setFirstName("Nemanja");
		u2.setLastName("Nemanjic");
		u2.setUsername("sava");
		u2.setPassword("$2a$10$L49EYcQMFQQOnOMxDkIv0.QwQNo1SYqMHKxY1iDDPxX8OF5Ovhnbu");
		u2.setRole(r1);
		
		userRepository.save(u1);
		userRepository.save(u2);
		
		Administrator a = new Administrator();
		a.setFirstName("Admir");
		a.setLastName("Admir");
		a.setUsername("admin");
		a.setPassword("$2a$10$L49EYcQMFQQOnOMxDkIv0.QwQNo1SYqMHKxY1iDDPxX8OF5Ovhnbu");
		a.setRole(r3);
		
		adminRepository.save(a);

		
	}

}
