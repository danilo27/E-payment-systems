package acq.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ACCOUNT_ID")
	private Long id;
	
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "ACCOUNT_BALANCE")
	private double accountBalance;
	
	@OneToOne
	private Card card; //for buyer
	
	private String merchantId; //for merchant 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Account(String accountNumber, double accountBalance, Card card) {
		super();
		
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.card = card;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	public Account(){}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountBalance=" + accountBalance
				+ ", card=" + card + ", merchantId=" + merchantId + "]";
	}
	 
	
	
	
}
