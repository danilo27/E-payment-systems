package pc.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pc.model.MerchantInfo;
import pc.model.PaymentFieldId;
import pc.dto.ApiResponse;
import pc.dto.MerchantToPcDto;
import pc.model.Merchant;
import pc.model.PaymentType;
import pc.repositories.MerchantInfoRepository;
import pc.repositories.MerchantRepository;
import pc.repositories.PaymentTypeRepository;

@RestController
@RequestMapping("/pc/merchant")
public class MerchantController {

	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private MerchantInfoRepository merchantInfoRepository;
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity <ApiResponse> newMerchant(@RequestBody MerchantToPcDto merchant){
		System.out.println("[PC] New Merchant: " + merchant.toString());
		
		Merchant merch = new Merchant();
		merch.setMerchantId(merchant.getMerchantId());
		merch.setSupportedPayments(merchant.getSupportedPayments());
		merchantRepository.save(merch);

		MerchantInfo miField = new MerchantInfo();
		
		for (Map.Entry<String, Map<String, String>> entry : merchant.getPaymentTypeFields().entrySet()){
		    System.out.println(entry.getKey() + "/" + entry.getValue()); //CARD
		    for (Map.Entry<String, String> fields : entry.getValue().entrySet()){
		    	miField = new MerchantInfo();
		    	miField.setPaymentFieldId(new PaymentFieldId(entry.getKey(),merchant.getMerchantId(), fields.getKey()));
		    	miField.setMerchant(merch);
		    	miField.setPaymentType(paymentTypeRepository.findByName(entry.getKey()).orElse(null));
		    	miField.setValue(fields.getValue());
		    	merchantInfoRepository.save(miField);
		    }
		}
		


		return new ResponseEntity<>(new ApiResponse("success", true), HttpStatus.OK);
	}
}
