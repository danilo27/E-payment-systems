package pc.conf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pc.model.Merchant;
import pc.model.PaymentType;
import pc.model.PaymentTypeField;
import pc.repositories.PaymentTypeFieldRepository;
import pc.repositories.PaymentTypeRepository;
import pc.services.MerchantService;

@Component
public class Data {
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private PaymentTypeFieldRepository paymentTypeFieldRepository;
	
	@PostConstruct
	private void init() {
		Merchant m1 = new Merchant("merchant1", "pas", "http://localhost:8081");
		
		paymentTypeRepository.deleteAll();
		
		PaymentType card = new PaymentType();
		card = paymentTypeRepository.save(card);
		List<PaymentTypeField> cardFields = new ArrayList<>();
		
		PaymentTypeField panField = paymentTypeFieldRepository.save(new PaymentTypeField("PAN","string"));
		PaymentTypeField cardHolderNameField = paymentTypeFieldRepository.save(new PaymentTypeField("Card Holder Name","string"));
		PaymentTypeField expiringMonthField = paymentTypeFieldRepository.save(new PaymentTypeField("Expiring Month","string"));
		PaymentTypeField expiringYearField = paymentTypeFieldRepository.save(new PaymentTypeField("Expiring Year","string"));
		PaymentTypeField cvField = paymentTypeFieldRepository.save(new PaymentTypeField("CV","string"));
	 
		
		cardFields.add(panField);
		cardFields.add(cardHolderNameField);
		cardFields.add(expiringMonthField);
		cardFields.add(expiringYearField);
		cardFields.add(cvField);
		
		card.setName("CARD");
		card.setImageUrl("https://farmaciaproderma.com/wp-content/uploads/2018/08/visa-mastercard-logo.jpg");
		card.setFields(cardFields);
		
		paymentTypeRepository.save(card);
		
		PaymentType paypal = new PaymentType();
		paypal.setName("PAYPAL");
		paypal.setImageUrl("https://yt3.ggpht.com/a-/AN66SAzETZ0qdNMqaKxIYRua6DYCPY6TSMeyckHnAA=s900-mo-c-c0xffffffff-rj-k-no");
		
		paymentTypeRepository.save(paypal);

		PaymentType bitcoin = new PaymentType();
		bitcoin.setName("BITCOIN");
		bitcoin.setImageUrl("http://mrjamie.cc/wp-content/uploads/2013/10/bitcoin-logo-1000.jpg");
		
		paymentTypeRepository.save(bitcoin);
		
	}
}
