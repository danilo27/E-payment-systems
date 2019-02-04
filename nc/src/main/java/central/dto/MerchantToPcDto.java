package central.dto;

import java.util.List;

import central.model.SupportedPayments;

public class MerchantToPcDto {

	
	private String merchantId;
	
	private String merchantPass;
	
	private String merchantBankUrl;
	
	List<SupportedPayments> supportedPayments;

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
	
	
}
