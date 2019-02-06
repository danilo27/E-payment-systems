package central.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import central.model.PaymentTypeField;

public class NewMerchantDto {

	private String magazineIssn;
	private List<Long> supportedPaymentsIds;
	private String merchantId;
	private String merchantPassword;
	private String merchantBankUrl;
	
	private Map<String, Map<String, String>> paymentTypeFields = new HashMap<>();
	
	public String getMagazineIssn() {
		return magazineIssn;
	}

	public void setMagazineIssn(String magazineIssn) {
		this.magazineIssn = magazineIssn;
	}

	public List<Long> getSupportedPaymentsIds() {
		return supportedPaymentsIds;
	}

	public void setSupportedPaymentsIds(List<Long> supportedPaymentsIds) {
		this.supportedPaymentsIds = supportedPaymentsIds;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getMerchantBankUrl() {
		return merchantBankUrl;
	}

	public void setMerchantBankUrl(String merchantBankUrl) {
		this.merchantBankUrl = merchantBankUrl;
	}

	public Map<String, Map<String, String>> getPaymentTypeFields() {
		return paymentTypeFields;
	}

	public void setPaymentTypeFields(Map<String, Map<String, String>> paymentTypeFields) {
		this.paymentTypeFields = paymentTypeFields;
	}

	@Override
	public String toString() {
		return "NewMerchantDto [magazineIssn=" + magazineIssn + ", supportedPaymentsIds=" + supportedPaymentsIds
				+ ", merchantId=" + merchantId + ", merchantPassword=" + merchantPassword + ", merchantBankUrl="
				+ merchantBankUrl + ", paymentTypeFields=" + paymentTypeFields + "]";
	}

	 
	

}
