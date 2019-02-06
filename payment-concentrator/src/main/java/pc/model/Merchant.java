package pc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
 
@Entity
public class Merchant {
	
	//ID prodavca, koji se dobije od banke prilikom registracije prodavca za
	//onlajn prodaju
	@Id
	private String merchantId;
	
	//lozinka koja se dobije od banke prilikom registracije prodavca
	//za onlajn prodaju (tip String(100))
	//@Column
	//private String merchantPass;
	
	//@Column
	//private String merchantBankUrl;
 
	@ManyToMany(fetch = FetchType.EAGER)
	private List<PaymentType> supportedPayments = new ArrayList<>();
	
	public Merchant(){} 

	public Merchant(String merchantId) {
		super();
		this.merchantId = merchantId;
		//this.merchantPass = merchantPass;
		//this.merchantBankUrl = merchantBankUrl;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
//	public String getMerchantPass() {
//		return merchantPass;
//	}
//
//	public void setMerchantPass(String merchantPass) {
//		this.merchantPass = merchantPass;
//	}
//
//	public String getMerchantBankUrl() {
//		return merchantBankUrl;
//	}
//
//	public void setMerchantBankUrl(String merchantBankUrl) {
//		this.merchantBankUrl = merchantBankUrl;
//	}

	public List<PaymentType> getSupportedPayments() {
		return supportedPayments;
	}

	public void setSupportedPayments(List<PaymentType> supportedPayments) {
		this.supportedPayments = supportedPayments;
	}

	 
	
	

}
