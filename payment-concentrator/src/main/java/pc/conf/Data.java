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
		card.setName("Card");
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
		paypal.setName("Paypal");
		paypal.setLabel("PayPal");
		paypal.setImageUrl("https://yt3.ggpht.com/a-/AN66SAzETZ0qdNMqaKxIYRua6DYCPY6TSMeyckHnAA=s900-mo-c-c0xffffffff-rj-k-no");
		paypal = paymentTypeRepository.save(paypal);
		
		List<PaymentTypeField> paypalFields = new ArrayList<>();
		
		PaymentTypeField paypalApiKey = new PaymentTypeField (paypal, "Api Key", "string");	
		paypalApiKey.setId(new KeyClass(paypal.getName(), "paypalApiKey"));
		
		PaymentTypeField paypalApiPassword = new PaymentTypeField (paypal, "Api Password", "string");	
		paypalApiPassword.setId(new KeyClass(paypal.getName(), "paypalApiPassword"));
		
		PaymentTypeField paypalPlanName = new PaymentTypeField (paypal, "Plan Name", "string");	
		paypalPlanName.setId(new KeyClass(paypal.getName(), "paypalPlanName"));
		
		PaymentTypeField paypalPlanDescription = new PaymentTypeField (paypal, "Plan Description", "string");	
		paypalPlanDescription.setId(new KeyClass(paypal.getName(), "paypalPlanDescription"));
		
		PaymentTypeField paypalFrequency = new PaymentTypeField (paypal, "Frequency", "string");	
		paypalFrequency.setId(new KeyClass(paypal.getName(), "paypalFrequency"));
		
		PaymentTypeField paypalFrequencyInterval = new PaymentTypeField (paypal, "Frequency Interval", "string");	
		paypalFrequencyInterval.setId(new KeyClass(paypal.getName(), "paypalFrequencyInterval"));

		PaymentTypeField paypalCycles = new PaymentTypeField (paypal, "Cycles", "string");	
		paypalCycles.setId(new KeyClass(paypal.getName(), "paypalCycles"));
		
		PaymentTypeField paypalCurrency = new PaymentTypeField (paypal, "Currency", "string");	
		paypalCurrency.setId(new KeyClass(paypal.getName(), "paypalCurrency"));
		
		PaymentTypeField paypalAmount = new PaymentTypeField (paypal, "Amount", "string");	
		paypalAmount.setId(new KeyClass(paypal.getName(), "paypalAmount"));
		
		PaymentTypeField paypalShippingAddress = new PaymentTypeField (paypal, "Shipping Address", "string");	
		paypalShippingAddress.setId(new KeyClass(paypal.getName(), "paypalShippingAddress"));
		
		PaymentTypeField paypalCity = new PaymentTypeField (paypal, "City", "string");	
		paypalCity.setId(new KeyClass(paypal.getName(), "paypalCity"));
		
		PaymentTypeField paypalStateCode = new PaymentTypeField (paypal, "State Code", "string");	
		paypalStateCode.setId(new KeyClass(paypal.getName(), "paypalStateCode"));
		
		PaymentTypeField paypalCountryCode = new PaymentTypeField (paypal, "Country Code", "string");	
		paypalCountryCode.setId(new KeyClass(paypal.getName(), "paypalCountryCode"));
		
		PaymentTypeField paypalPostalCode = new PaymentTypeField (paypal, "Postal Code", "string");	
		paypalPostalCode.setId(new KeyClass(paypal.getName(), "paypalPostalCode"));
		
		paypalApiKey = paymentTypeFieldRepository.save(paypalApiKey);
		paypalApiPassword = paymentTypeFieldRepository.save(paypalApiPassword);
		paypalPlanName = paymentTypeFieldRepository.save(paypalPlanName);
		paypalPlanDescription = paymentTypeFieldRepository.save(paypalPlanDescription);
		paypalFrequency = paymentTypeFieldRepository.save(paypalFrequency);
		paypalFrequencyInterval = paymentTypeFieldRepository.save(paypalFrequencyInterval);
		paypalCycles = paymentTypeFieldRepository.save(paypalCycles);
		paypalCurrency = paymentTypeFieldRepository.save(paypalCurrency);
		paypalAmount = paymentTypeFieldRepository.save(paypalAmount);
		paypalShippingAddress = paymentTypeFieldRepository.save(paypalShippingAddress);
		paypalCity = paymentTypeFieldRepository.save(paypalCity);
		paypalStateCode = paymentTypeFieldRepository.save(paypalStateCode);
		paypalCountryCode = paymentTypeFieldRepository.save(paypalCountryCode);
		paypalPostalCode = paymentTypeFieldRepository.save(paypalPostalCode);
				
		paypalFields.add(paypalApiKey);		
		paypalFields.add(paypalApiPassword);	
		paypalFields.add(paypalPlanName);
		paypalFields.add(paypalPlanDescription);
		paypalFields.add(paypalFrequency);
		paypalFields.add(paypalFrequencyInterval);
		paypalFields.add(paypalCycles);
		paypalFields.add(paypalCurrency);
		paypalFields.add(paypalAmount);
		paypalFields.add(paypalShippingAddress);
		paypalFields.add(paypalCity);
		paypalFields.add(paypalStateCode);
		paypalFields.add(paypalCountryCode);
		paypalFields.add(paypalPostalCode);

		paypal.setFields(paypalFields);
		
		paymentTypeRepository.save(paypal);


		
		PaymentType bitcoin = new PaymentType();
		bitcoin.setName("Bitcoin");
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
		daniloMerchant.setSuccessUrl("http://localhost:4204/#/success");
		daniloMerchant.setFailedUrl("http://localhost:4204/#/failed");
		daniloMerchant.setErrorUrl("http://localhost:4204/#/error");
		
		List<PaymentType> supportedPayments = new ArrayList<>();

		supportedPayments.add(paymentTypeRepository.findByName("Card").orElse(null));
		supportedPayments.add(paymentTypeRepository.findByName("Paypal").orElse(null));
		supportedPayments.add(paymentTypeRepository.findByName("Bitcoin").orElse(null));

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
		
		MerchantInfo paypalApiKeyInfo = new MerchantInfo();
		paypalApiKeyInfo.setPaymentFieldId(new PaymentFieldId(paypal.getName(),daniloMerchant.getMerchantId(),paypalApiKey.getId().getFieldName()));
		paypalApiKeyInfo.setMerchant(daniloMerchant);
		paypalApiKeyInfo.setPaymentType(paypal);
		paypalApiKeyInfo.setPaymentTypeField(paypalApiKey);
		paypalApiKeyInfo.setValue("AWSFgD4EBA8g6SrzszTOTrtw5PfBEalEMszEWja7eo9eZNJHt9QgxRdglWGRrqNL1sICvMKhWKolE71o");
		
		MerchantInfo paypalApiPasswordInfo = new MerchantInfo();
		paypalApiPasswordInfo.setPaymentFieldId(new PaymentFieldId(paypal.getName(),daniloMerchant.getMerchantId(),paypalApiPassword.getId().getFieldName()));
		paypalApiPasswordInfo.setMerchant(daniloMerchant);
		paypalApiPasswordInfo.setPaymentType(paypal);
		paypalApiPasswordInfo.setPaymentTypeField(paypalApiPassword);
		paypalApiPasswordInfo.setValue("EAbj-IqR0uJb2-mNM8pX1e-3e_ZoYJ4hkiU11xct6T_TMM4uH1P9nrnNi4_hBDWqJGbhEuiL9uTejSbr");
		
		merchantInfoRepository.save(paypalApiKeyInfo);
		merchantInfoRepository.save(paypalApiPasswordInfo);
	
		//TODO dodati podatke za bitcoin
		
		
		
		
	
		
		
 
		
			
		
	}
}
