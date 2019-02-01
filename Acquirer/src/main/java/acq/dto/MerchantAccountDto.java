package acq.dto;

public class MerchantAccountDto {
	private String merchantId;
	private String merchantPassword;
	public MerchantAccountDto(){}
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
	
}
