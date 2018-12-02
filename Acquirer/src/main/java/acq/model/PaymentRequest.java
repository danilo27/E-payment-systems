package acq.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "PAYMENT_REQUEST")
public class PaymentRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String token = UUID.randomUUID().toString();
	
	@Id
	@GeneratedValue
	@Column(name = "PAYMENT_REQUEST_ID")
	private Long id;
	
	@Column(name = "MERCHANT_ID", length = 30)
	private String merchantId;
	
	@Column(name = "MERCHANT_PASSWORD", length = 100)
	private String merchantPassword;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@Column(name = "MERCHANT_ORDER_ID")
	private Integer merchantOrderId;
	
	@Column(name = "MERCHANT_TIMESTAMP")
	private Timestamp merchantTimestamp;
	
	@Column(name = "SUCCESS_URL")
	private String successUrl;
	
	@Column(name = "FAILED_URL")
	private String failedUrl;
	
	@Column(name = "ERROR_URL")
	private String errorUrl;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PAYMENT_URL_AND_ID")
	private PaymentURLandID paymentUrlAndId;
	
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Integer merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Timestamp getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Timestamp merchantTimestamp) {
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

	public PaymentURLandID getPaymentUrlAndId() {
		return paymentUrlAndId;
	}

	public void setPaymentUrlAndId(PaymentURLandID paymentUrlAndId) {
		this.paymentUrlAndId = paymentUrlAndId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PaymentRequest(Long id, String merchantId, String merchantPassword, BigDecimal amount,
			Integer merchantOrderId, Timestamp merchantTimestamp, String successUrl, String failedUrl, String errorUrl,
			PaymentURLandID paymentUrlAndId) {
		super();
		this.id = id;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
		this.paymentUrlAndId = paymentUrlAndId;
	}

	
}
