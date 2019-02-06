	package pc.conf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pc.model.KeyClass;
import pc.model.Merchant;
import pc.model.MerchantInfo;
import pc.model.PaymentFieldId;
import pc.model.PaymentType;
import pc.model.PaymentTypeField;
import pc.repositories.MerchantInfoRepository;
import pc.repositories.MerchantRepository;
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
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private MerchantInfoRepository merchantInfoRepository;
	
	@PostConstruct
	private void init() {

		paymentTypeRepository.deleteAll();
		
		PaymentType card = new PaymentType();
		card.setName("CARD");
		card.setLabel("Card");
		card.setImageUrl("https://farmaciaproderma.com/wp-content/uploads/2018/08/visa-mastercard-logo.jpg");
		card = paymentTypeRepository.save(card);
		List<PaymentTypeField> cardFields = new ArrayList<>();
 
		PaymentTypeField cardMerchantId = new PaymentTypeField( card, "Merchant Id", "string");
		cardMerchantId.setId(new KeyClass(card.getName(), "merchantId"));
 
		PaymentTypeField cardMerchantPassword = new PaymentTypeField(card,"Merchant Password", "string");
		cardMerchantPassword.setId(new KeyClass(card.getName(), "merchantPassword"));
		
		PaymentTypeField cardMerchantBankUrl = new PaymentTypeField(card,"Merchant Bank Url", "string");
		cardMerchantBankUrl.setId(new KeyClass(card.getName(), "merchantBankUrl"));
		
		cardMerchantId = paymentTypeFieldRepository.save(cardMerchantId);
		cardMerchantPassword = paymentTypeFieldRepository.save(cardMerchantPassword);
		cardMerchantBankUrl = paymentTypeFieldRepository.save(cardMerchantBankUrl);

		cardFields.add(cardMerchantId);
		cardFields.add(cardMerchantPassword);
		cardFields.add(cardMerchantBankUrl);
		
		card.setFields(cardFields);

		paymentTypeRepository.save(card);
		
		
		
		
		
		PaymentType paypal = new PaymentType();
		paypal.setName("PAYPAL");
		paypal.setLabel("PayPal");
		paypal.setImageUrl("https://yt3.ggpht.com/a-/AN66SAzETZ0qdNMqaKxIYRua6DYCPY6TSMeyckHnAA=s900-mo-c-c0xffffffff-rj-k-no");
		paypal = paymentTypeRepository.save(paypal);
		
		//TODO dodati ostala polja potrebna merchantu za paypal
		List<PaymentTypeField> paypalFields = new ArrayList<>();
		
		PaymentTypeField paypalMerchantId = new PaymentTypeField (paypal, "Merchant Id", "string");
		
		paypalMerchantId.setId(new KeyClass(paypal.getName(), "merchantId"));
		
		paypalMerchantId = paymentTypeFieldRepository.save(paypalMerchantId);
		
		paypalFields.add(paypalMerchantId);
		
		paypal.setFields(paypalFields);
		
		paymentTypeRepository.save(paypal);


		
		
		
		PaymentType bitcoin = new PaymentType();
		bitcoin.setName("BITCOIN");
		bitcoin.setLabel("Bitcoin");
		bitcoin.setImageUrl("http://mrjamie.cc/wp-content/uploads/2013/10/bitcoin-logo-1000.jpg");
		bitcoin = paymentTypeRepository.save(bitcoin);
		
		//TODO dodati ostala polja potrebna merchantu za bitcoin
		List<PaymentTypeField> bitcoinFields = new ArrayList<>();

		PaymentTypeField bitcoinApi = new PaymentTypeField(bitcoin,"API", "string");
		
		bitcoinApi.setId(new KeyClass(bitcoin.getName(), "api"));
		
		bitcoinApi = paymentTypeFieldRepository.save(bitcoinApi);
		
		bitcoinFields.add(bitcoinApi);
		
		bitcoin.setFields(bitcoinFields);
 	
		paymentTypeRepository.save(bitcoin);
		
		
		
		
		
		
		
		
		
		merchantRepository.deleteAll(); 
		
		Merchant daniloMerchant = new Merchant("12345678");
		//daniloMerchant.setMerchantBankUrl("http://localhost:8081"); //uzeti iz MerchantInfo
		List<PaymentType> supportedPayments = new ArrayList<>();
		supportedPayments.add(paymentTypeRepository.findByName("CARD").orElse(null));
		supportedPayments.add(paymentTypeRepository.findByName("PAYPAL").orElse(null));
		supportedPayments.add(paymentTypeRepository.findByName("BITCOIN").orElse(null));
		daniloMerchant.setSupportedPayments(supportedPayments);
		merchantRepository.save(daniloMerchant);
	
		MerchantInfo cardMerchantIdInfo = new MerchantInfo();
		cardMerchantIdInfo.setPaymentFieldId(new PaymentFieldId(card.getName(),daniloMerchant.getMerchantId(),cardMerchantId.getId().getFieldName()));
		cardMerchantIdInfo.setMerchant(daniloMerchant);
		cardMerchantIdInfo.setPaymentType(card);
		cardMerchantIdInfo.setPaymentTypeField(cardMerchantId);
		cardMerchantIdInfo.setValue("daniloMerchant");
		
		MerchantInfo cardMerchantPasswordInfo = new MerchantInfo();
		cardMerchantPasswordInfo.setPaymentFieldId(new PaymentFieldId(card.getName(),daniloMerchant.getMerchantId(),cardMerchantPassword.getId().getFieldName()));
		cardMerchantPasswordInfo.setMerchant(daniloMerchant);
		cardMerchantPasswordInfo.setPaymentType(card);
		cardMerchantPasswordInfo.setPaymentTypeField(cardMerchantPassword);
		cardMerchantPasswordInfo.setValue("pas");
		
		MerchantInfo cardMerchantBankUrlInfo = new MerchantInfo();
		cardMerchantBankUrlInfo.setPaymentFieldId(new PaymentFieldId(card.getName(),daniloMerchant.getMerchantId(),cardMerchantBankUrl.getId().getFieldName()));
		cardMerchantBankUrlInfo.setMerchant(daniloMerchant);
		cardMerchantBankUrlInfo.setPaymentType(card);
		cardMerchantBankUrlInfo.setPaymentTypeField(cardMerchantBankUrl);
		cardMerchantBankUrlInfo.setValue("http://localhost:8081");
		
		merchantInfoRepository.save(cardMerchantIdInfo);
		merchantInfoRepository.save(cardMerchantPasswordInfo);
		merchantInfoRepository.save(cardMerchantBankUrlInfo);
		
		//System.out.println(merchantInfoRepository.findMerchantData("CARD", "daniloMerchant", "merchantId").getValue());
		
		
		
		
		
		
		
//		Merchant drugiMerchant = new Merchant("drugiMerchant", "pas", null);
//		//drugiMerchant.setMerchantBankUrl("http://localhost:8081");
//		supportedPayments = new ArrayList<>();
//		supportedPayments.add(paymentTypeRepository.findByName("PAYPAL").orElse(null));
//		supportedPayments.add(paymentTypeRepository.findByName("BITCOIN").orElse(null));
//		drugiMerchant.setSupportedPayments(supportedPayments);
//		merchantRepository.save(drugiMerchant);
		
		
		
		
		
 
		
		
		
		
		
	}
}
