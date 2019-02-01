package central.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Merchant {

	@Id
	private String merchantId;
	
	@Column
	private String merchantPassword;
	
	@OneToOne
	private Magazine magazine;
	
	@ManyToMany
	private Set<SupportedPayments> supportedPayments = new HashSet<SupportedPayments>();

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

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public Set<SupportedPayments> getSupportedPayments() {
		return supportedPayments;
	}

	public void setSupportedPayments(Set<SupportedPayments> supportedPayments) {
		this.supportedPayments = supportedPayments;
	}
	
	
}
