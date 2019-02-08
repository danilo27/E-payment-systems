package pc.model;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Cart {
	@Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
	
	@Column
	private String merchantId;
	
	@Column
	private String merchantPassword;
	
	@Column
	private Long merchantOrderId;
	
	@Column
	private Date merchantTimestamp;
	
	@Column 	//inProgress, successful, error, cancelled
	private String status;
	
	@Column
	private Double totalPrice;
	 
	@Transient
	private HashMap<String, String> itemDetails = new HashMap<String, String>();
	
	@Column(unique = true)
	private String paymentId;
 
	@Column
	private String token;
	
	
	public Cart(){}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public HashMap<String, String> getItemDetails() {
		return itemDetails;
	}
	public void setItemDetails(HashMap<String, String> itemDetails) {
		this.itemDetails = itemDetails;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
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

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", merchantId=" + merchantId + ", merchantPassword=" + merchantPassword
				+ ", merchantOrderId=" + merchantOrderId + ", merchantTimestamp=" + merchantTimestamp + ", status="
				+ status + ", totalPrice=" + totalPrice + ", itemDetails=" + itemDetails + ", token=" + token + "]";
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	
 
	 

	 
	
}