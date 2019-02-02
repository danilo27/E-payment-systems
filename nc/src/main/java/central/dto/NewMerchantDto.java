package central.dto;

import java.util.List;

public class NewMerchantDto {

	private String magazineIssn;
	private List<Long> supportedPaymentsIds;

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

}
