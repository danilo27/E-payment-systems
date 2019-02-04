package pc.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PAYMENT_REQUEST")
public class PaymentRequest implements Serializable {

	private String token = UUID.randomUUID().toString();

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "PAYMENT_REQUEST_ID")
	private Long id;

	@Column(name = "MERCHANT_ID", length = 30)
	private String merchantId;

	@Column(name = "MERCHANT_PASSWORD", length = 100)
	private String merchantPassword;

	@Column(name = "AMOUNT")
	private Double amount;

	@Column(name = "MERCHANT_ORDER_ID")
	private Long merchantOrderId;

	@Column(name = "MERCHANT_TIMESTAMP")
	private Date merchantTimestamp;

	@Column(name = "SUCCESS_URL")
	private String successUrl;

	@Column(name = "FAILED_URL")
	private String failedUrl;

	@Column(name = "ERROR_URL")
	private String errorUrl;

	private String paymentTypeName;

	private String currency;


	
	
	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

 

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PaymentRequest() {
	}

	 
	public PaymentRequest(String token, Long id, String merchantId, String merchantPassword, Double amount,
			Long merchantOrderId, Date merchantTimestamp, String successUrl, String failedUrl, String errorUrl,
			PaymentType paymentType, String currency) {
		super();
		this.token = token;
		this.id = id;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
		//this.paymentType = paymentType;
		this.currency = currency;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "PaymentRequest [token=" + token + ", id=" + id + ", merchantId=" + merchantId + ", merchantPassword="
				+ merchantPassword + ", amount=" + amount + ", merchantOrderId=" + merchantOrderId
				+ ", merchantTimestamp=" + merchantTimestamp + ", successUrl=" + successUrl + ", failedUrl=" + failedUrl
				+ ", errorUrl=" + errorUrl + ", "  + ", currency=" + currency + "]";
	}

}
