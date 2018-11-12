package acq.model;

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
	private Long pan;
	
	@Size(min = 3, max = 4)
	@Column(name = "SECURITY_CODE")
	private Long securityCode;
	
	@Size(min = 1, max = 50)
	@Column(name = "CARD_HOLDER_NAME")
	private String cardHolderName;
	
	@Column(name = "EXPIRING_DATE")
	private Date expiringDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPan() {
		return pan;
	}

	public void setPan(Long pan) {
		this.pan = pan;
	}

	public Long getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(Long securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getExpiringDate() {
		return expiringDate;
	}

	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Card(Long id, @Size(min = 8, max = 16) Long pan, @Size(min = 3, max = 4) Long securityCode,
			@Size(min = 1, max = 50) String cardHolderName, Date expiringDate) {
		super();
		this.id = id;
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.expiringDate = expiringDate;
	}
	
	
}
