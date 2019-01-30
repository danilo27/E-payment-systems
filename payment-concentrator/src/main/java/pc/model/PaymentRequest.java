package pc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pc.model.enums.PaymentType;

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

	// MERCHANT_PASSWORD – lozinka koja se dobije od banke
	// prilikom registracije prodavca za onlajn prodaju (tip String(100)),
	@Column(name = "MERCHANT_PASSWORD", length = 100)
	private String merchantPassword;

	// iznos transakcije (tip Decimal(10, 2)),
	@Column(name = "AMOUNT")
	private Long amount;

	// prodavčev ID transakcije (tip Number(10)),
	@Column(name = "MERCHANT_ORDER_ID")
	private Integer merchantOrderId;

	// MERCHANT_TIMESTAMP – prodavčev timestamp transakcije (tip DateTime).
	@Column(name = "MERCHANT_TIMESTAMP")
	private Date merchantTimestamp;

	// SUCCESS_URL – URL na koji će se kupac preusmeriti ako je transakcija
	// uspešna
	@Column(name = "SUCCESS_URL")
	private String successUrl;

	// FAILED_URL - URL na koji će se kupac preusmeriti ako je transakcija
	// neuspešna
	@Column(name = "FAILED_URL")
	private String failedUrl;

	// ERROR_URL – URL na koji će se kupac preusmeriti ako se desi bilo kakva
	// greška.
	@Column(name = "ERROR_URL")
	private String errorUrl;

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "PAYMENT_URL_AND_ID")
	// private PaymentURLandID paymentUrlAndId;

	private PaymentType paymentType;

	private String currency;

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Integer merchantOrderId) {
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

	// public PaymentURLandID getPaymentUrlAndId() {
	// return paymentUrlAndId;
	// }
	//
	// public void setPaymentUrlAndId(PaymentURLandID paymentUrlAndId) {
	// this.paymentUrlAndId = paymentUrlAndId;
	// }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PaymentRequest() {
	}

	public PaymentRequest(Long id, String merchantId, String merchantPassword, Long amount, Integer merchantOrderId,
			Timestamp merchantTimestamp, String successUrl, String failedUrl, String errorUrl) {
		super();
		this.id = id;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = new Date();
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
		// this.paymentUrlAndId = paymentUrlAndId;
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
				+ ", errorUrl=" + errorUrl + ", paymentType=" + paymentType + ", currency=" + currency + "]";
	}

}
