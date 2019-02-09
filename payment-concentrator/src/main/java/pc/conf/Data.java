	package pc.conf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${nc.frontend}")
	private String ncUrl;
	
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
		daniloMerchant.setSuccessUrl(ncUrl + "/#/success");
		daniloMerchant.setFailedUrl(ncUrl + "/#/failed");
		daniloMerchant.setErrorUrl(ncUrl + "/#/error");
		
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

		
		Merchant drugiMerchant = new Merchant("87654321");
		drugiMerchant.setSuccessUrl(ncUrl + "/#/success");
		drugiMerchant.setFailedUrl(ncUrl + "/#/failed");
		drugiMerchant.setErrorUrl(ncUrl + "/#/error");
		
		List<PaymentType> supportedPayments2 = new ArrayList<>();

		supportedPayments2.add(paymentTypeRepository.findByName("Card").orElse(null));
		supportedPayments2.add(paymentTypeRepository.findByName("Paypal").orElse(null));
		supportedPayments2.add(paymentTypeRepository.findByName("Bitcoin").orElse(null));

		drugiMerchant.setSupportedPayments(supportedPayments2);
		merchantRepository.save(drugiMerchant);
	
		MerchantInfo card2MerchantIdInfo = new MerchantInfo();
		card2MerchantIdInfo.setPaymentFieldId(new PaymentFieldId(card.getName(),drugiMerchant.getMerchantId(),cardMerchantId.getId().getFieldName()));
		card2MerchantIdInfo.setMerchant(drugiMerchant);
		card2MerchantIdInfo.setPaymentType(card);
		card2MerchantIdInfo.setPaymentTypeField(cardMerchantId);
		card2MerchantIdInfo.setValue("drugiMerchant");
		
		MerchantInfo card2MerchantPasswordInfo = new MerchantInfo();
		card2MerchantPasswordInfo.setPaymentFieldId(new PaymentFieldId(card.getName(),drugiMerchant.getMerchantId(),cardMerchantPassword.getId().getFieldName()));
		card2MerchantPasswordInfo.setMerchant(drugiMerchant);
		card2MerchantPasswordInfo.setPaymentType(card);
		card2MerchantPasswordInfo.setPaymentTypeField(cardMerchantPassword);
		card2MerchantPasswordInfo.setValue("pas");
		
		MerchantInfo card2MerchantBankUrlInfo = new MerchantInfo();
		card2MerchantBankUrlInfo.setPaymentFieldId(new PaymentFieldId(card.getName(),drugiMerchant.getMerchantId(),cardMerchantBankUrl.getId().getFieldName()));
		card2MerchantBankUrlInfo.setMerchant(drugiMerchant);
		card2MerchantBankUrlInfo.setPaymentType(card);
		card2MerchantBankUrlInfo.setPaymentTypeField(cardMerchantBankUrl);
		card2MerchantBankUrlInfo.setValue("http://localhost:8081");
		
		merchantInfoRepository.save(card2MerchantIdInfo);
		merchantInfoRepository.save(card2MerchantPasswordInfo);
		merchantInfoRepository.save(card2MerchantBankUrlInfo);
		
		MerchantInfo paypal2ApiKeyInfo = new MerchantInfo();
		paypal2ApiKeyInfo.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalApiKey.getId().getFieldName()));
		paypal2ApiKeyInfo.setMerchant(drugiMerchant);
		paypal2ApiKeyInfo.setPaymentType(paypal);
		paypal2ApiKeyInfo.setPaymentTypeField(paypalApiKey);
		paypal2ApiKeyInfo.setValue("AWSFgD4EBA8g6SrzszTOTrtw5PfBEalEMszEWja7eo9eZNJHt9QgxRdglWGRrqNL1sICvMKhWKolE71o");
		
		
		MerchantInfo paypal2ApiPasswordInfo = new MerchantInfo();
		paypal2ApiPasswordInfo.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalApiPassword.getId().getFieldName()));
		paypal2ApiPasswordInfo.setMerchant(drugiMerchant);
		paypal2ApiPasswordInfo.setPaymentType(paypal);
		paypal2ApiPasswordInfo.setPaymentTypeField(paypalApiPassword);
		paypal2ApiPasswordInfo.setValue("EAbj-IqR0uJb2-mNM8pX1e-3e_ZoYJ4hkiU11xct6T_TMM4uH1P9nrnNi4_hBDWqJGbhEuiL9uTejSbr");
		
		//----------------------------
		
		
		MerchantInfo planName = new MerchantInfo();
		planName.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalPlanName.getId().getFieldName()));
		planName.setMerchant(drugiMerchant);
		planName.setPaymentType(paypal);
		planName.setPaymentTypeField(paypalPlanName);
		planName.setValue("T-Shirt of the Month Club Plan");
		
		
		MerchantInfo planDescription = new MerchantInfo();
		planDescription.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalPlanDescription.getId().getFieldName()));
		planDescription.setMerchant(drugiMerchant);
		planDescription.setPaymentType(paypal);
		planDescription.setPaymentTypeField(paypalPlanDescription);
		planDescription.setValue("Template creation.");
		
		
		MerchantInfo frequency = new MerchantInfo();
		frequency.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalFrequency.getId().getFieldName()));
		frequency.setMerchant(drugiMerchant);
		frequency.setPaymentType(paypal);
		frequency.setPaymentTypeField(paypalFrequency);
		frequency.setValue("MONTH");
		
		
		MerchantInfo frequencyInterval = new MerchantInfo();
		frequencyInterval.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalFrequencyInterval.getId().getFieldName()));
		frequencyInterval.setMerchant(drugiMerchant);
		frequencyInterval.setPaymentType(paypal);
		frequencyInterval.setPaymentTypeField(paypalFrequencyInterval);
		frequencyInterval.setValue("1");
		
		
		MerchantInfo cycles = new MerchantInfo();
		cycles.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalCycles.getId().getFieldName()));
		cycles.setMerchant(drugiMerchant);
		cycles.setPaymentType(paypal);
		cycles.setPaymentTypeField(paypalCycles);
		cycles.setValue("12");
		
		
		MerchantInfo currency = new MerchantInfo();
		currency.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalCurrency.getId().getFieldName()));
		currency.setMerchant(drugiMerchant);
		currency.setPaymentType(paypal);
		currency.setPaymentTypeField(paypalCurrency);
		currency.setValue("USD");
		
		
		MerchantInfo amount = new MerchantInfo();
		amount.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalAmount.getId().getFieldName()));
		amount.setMerchant(drugiMerchant);
		amount.setPaymentType(paypal);
		amount.setPaymentTypeField(paypalAmount);
		amount.setValue("4");
		
		
		MerchantInfo shippingAddress = new MerchantInfo();
		shippingAddress.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalShippingAddress.getId().getFieldName()));
		shippingAddress.setMerchant(drugiMerchant);
		shippingAddress.setPaymentType(paypal);
		shippingAddress.setPaymentTypeField(paypalShippingAddress);
		shippingAddress.setValue("111 First Street");
		
		
		MerchantInfo stateCode = new MerchantInfo();
		stateCode.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalStateCode.getId().getFieldName()));
		stateCode.setMerchant(drugiMerchant);
		stateCode.setPaymentType(paypal);
		stateCode.setPaymentTypeField(paypalStateCode);
		stateCode.setValue("CA");
		
		MerchantInfo countryCode = new MerchantInfo();
		countryCode.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalCountryCode.getId().getFieldName()));
		countryCode.setMerchant(drugiMerchant);
		countryCode.setPaymentType(paypal);
		countryCode.setPaymentTypeField(paypalCountryCode);
		countryCode.setValue("US");

		MerchantInfo postalCode = new MerchantInfo();
		postalCode.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalPostalCode.getId().getFieldName()));
		postalCode.setMerchant(drugiMerchant);
		postalCode.setPaymentType(paypal);
		postalCode.setPaymentTypeField(paypalPostalCode);
		postalCode.setValue("95070");
		
		MerchantInfo city = new MerchantInfo();
		city.setPaymentFieldId(new PaymentFieldId(paypal.getName(),drugiMerchant.getMerchantId(),paypalCity.getId().getFieldName()));
		city.setMerchant(drugiMerchant);
		city.setPaymentType(paypal);
		city.setPaymentTypeField(paypalCity);
		city.setValue("Saratoga");
		
		//------------------
		merchantInfoRepository.save(paypal2ApiKeyInfo);
		merchantInfoRepository.save(paypal2ApiPasswordInfo);
		
		merchantInfoRepository.save(planName);
		merchantInfoRepository.save(planDescription);
		merchantInfoRepository.save(frequency);
		merchantInfoRepository.save(frequencyInterval);
		merchantInfoRepository.save(cycles);
		merchantInfoRepository.save(currency);
		merchantInfoRepository.save(amount);
		merchantInfoRepository.save(shippingAddress);
		merchantInfoRepository.save(stateCode);
		merchantInfoRepository.save(countryCode);
		merchantInfoRepository.save(postalCode);
		merchantInfoRepository.save(city);
	
		
		//TODO dodati podatke za bitcoin
 
		
			
		
	}
}
