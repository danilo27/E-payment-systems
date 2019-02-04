package acq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Merchant {
	
	@Id
	@GeneratedValue
	private Long id;
	
	//ID prodavca, koji se dobije od banke prilikom registracije prodavca za
	//onlajn prodaju
	private String merchantId;
	
	//lozinka koja se dobije od banke prilikom registracije prodavca
	//za onlajn prodaju (tip String(100))
	private String merchantPass;
	
	private String merchantBankUrl;
	
	public Merchant(){} 

	public Merchant(String merchantId, String merchantPass, String merchantBankUrl) {
		super();
		this.merchantId = merchantId;
		this.merchantPass = merchantPass;
		this.merchantBankUrl = merchantBankUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Merchant [id=" + id + ", merchantId=" + merchantId + ", merchantPass=" + merchantPass
				+ ", merchantBankUrl=" + merchantBankUrl + "]";
	}
	
	
}
