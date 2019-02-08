package central.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import central.model.PaymentTypeField;
import central.model.SupportedPayments;

public class MerchantToPcDto {

	
	private String merchantId;
	
	private String merchantPass;
	
	private String merchantBankUrl;
	
	List<SupportedPayments> supportedPayments;
	
	private String successUrl;
	private String failedUrl;
	private String errorUrl;

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

	public List<SupportedPayments> getSupportedPayments() {
		return supportedPayments;
	}

	public String getMerchantBankUrl() {
		return merchantBankUrl;
	}

	public void setMerchantBankUrl(String merchantBankUrl) {
		this.merchantBankUrl = merchantBankUrl;
	}

	public void setSupportedPayments(List<SupportedPayments> supportedPayments) {
		this.supportedPayments = supportedPayments;
	}

	public Map<String, Map<String, String>> getPaymentTypeFields() {
		return paymentTypeFields;
	}

	public void setPaymentTypeFields(Map<String, Map<String, String>> paymentTypeFields) {
		this.paymentTypeFields = paymentTypeFields;
	}

	public MerchantToPcDto(){}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	
	
}
