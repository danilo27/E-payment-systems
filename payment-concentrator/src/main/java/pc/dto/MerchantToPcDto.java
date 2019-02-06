package pc.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pc.model.PaymentTypeField;
import pc.model.PaymentType;

public class MerchantToPcDto {

	
	private String merchantId;
	
	private String merchantPass;
	
	private String merchantBankUrl;
	
	List<PaymentType> supportedPayments;

	private Map<String, Map<String, String>> paymentTypeFields = new HashMap<>();
		
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}



	public String getMerchantPass() {
		return merchantPass;
	}

	public void setMerchantPass(String merchantPass) {
		this.merchantPass = merchantPass;
	}
 

	public String getMerchantBankUrl() {
		return merchantBankUrl;
	}

	public void setMerchantBankUrl(String merchantBankUrl) {
		this.merchantBankUrl = merchantBankUrl;
	}

 

	public List<PaymentType> getSupportedPayments() {
		return supportedPayments;
	}

	public void setSupportedPayments(List<PaymentType> supportedPayments) {
		this.supportedPayments = supportedPayments;
	}

	public Map<String, Map<String, String>> getPaymentTypeFields() {
		return paymentTypeFields;
	}

	public void setPaymentTypeFields(Map<String, Map<String, String>> paymentTypeFields) {
		this.paymentTypeFields = paymentTypeFields;
	}

	public MerchantToPcDto(){}
	
	
}
