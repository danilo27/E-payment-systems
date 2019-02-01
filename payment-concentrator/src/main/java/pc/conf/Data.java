package pc.conf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pc.model.Merchant;
import pc.model.PaymentType;
import pc.model.PaymentTypeField;
import pc.repositories.PaymentTypeRepository;
import pc.services.MerchantService;

@Component
public class Data {
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@PostConstruct
	private void init() {
//		Merchant m1 = new Merchant("merchant1", "pas", "http://localhost:8081");
//		
//		paymentTypeRepository.deleteAll();
//		
//		PaymentType card = new PaymentType();
//		List<PaymentTypeField> cardFields = new ArrayList<>();
//		cardFields.add(new PaymentTypeField("PAN","string"));
//		cardFields.add(new PaymentTypeField("Card Holder Name","string"));
//		cardFields.add(new PaymentTypeField("Expiring Month", "string"));
//		cardFields.add(new PaymentTypeField("Expiring Year", "string"));
//		cardFields.add(new PaymentTypeField("CV","string"));
//		card.setName("CARD");
//		card.setImageUrl("https://farmaciaproderma.com/wp-content/uploads/2018/08/visa-mastercard-logo.jpg");
//		card.setFields(cardFields);
//		
//		paymentTypeRepository.save(card);
		
		//TODO paypal
		
		//TODO bitcoin
		
	}
}
