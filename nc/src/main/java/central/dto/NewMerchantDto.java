package central.dto;

import java.util.List;

public class NewMerchantDto {

	private String magazineIssn;
	private List<Long> supportedPaymentsIds;
	private String merchantId;
	private String merchantPassword;
	private String merchantBankUrl;
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
	

}
