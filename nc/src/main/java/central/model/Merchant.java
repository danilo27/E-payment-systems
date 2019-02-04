package central.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Merchant {

	@Id
	private String merchantId;
	
	@Column
	private String merchantPass;
	
	private String merchantBankUrl;
	
	//@JsonIgnoreProperties("merchant")
	@JsonIgnore
	 @OneToOne
	private Magazine magazine;
	
	/*
	@ManyToMany
	private Set<SupportedPayments> supportedPayments = new HashSet<SupportedPayments>();
*/
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

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}
/*
	public Set<SupportedPayments> getSupportedPayments() {
		return supportedPayments;
	}

	public void setSupportedPayments(Set<SupportedPayments> supportedPayments) {
		this.supportedPayments = supportedPayments;
	}*/

	public String getMerchantBankUrl() {
		return merchantBankUrl;
	}

	public void setMerchantBankUrl(String merchantBankUrl) {
		this.merchantBankUrl = merchantBankUrl;
	}
/*
	@Override
	public String toString() {
		return "Merchant [merchantId=" + merchantId + ", merchantPass=" + merchantPass + ", merchantBankUrl="
				+ merchantBankUrl + ", magazine=" + magazine + "]";
	}
	*/
	
}
