package iss.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CARD")
public class Card implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "CARD_ID")
	private Long id;
	
	@Size(min = 8, max = 16)
	@Column(name = "PAN")
	private String pan;
	
	//@Size(min = 3, max = 4)
	@Column(name = "SECURITY_CODE")
	private int securityCode;
	
	@Size(min = 1, max = 50)
	@Column(name = "CARD_HOLDER_NAME")
	private String cardHolderName;
	
	@Column(name = "EXPIRING_DATE")
	private String expiringDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpiringDate() {
		return expiringDate;
	}

	public void setExpiringDate(String expiringDate) {
		this.expiringDate = expiringDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Card( @Size(min = 8, max = 16) String pan, int securityCode,
			@Size(min = 1, max = 50) String cardHolderName, String expiringDate) {
		super();
		
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.expiringDate = expiringDate;
	}
	
	public Card() {}
	
}
