package acq.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT")
public class Payment implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	private String paymentUrl;
	private String merchantId;
	private String merchantPassword;
	private int paymentId;
	private Long paymentRequestId;
	private String message;
	private String paymentRequestToken;
	private Double amount;
	private Long merchantOrderId;
	private String successUrl;
	private String errorUrl;
	private String failedUrl;
	public Payment(){}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	public String getErrorUrl() {
		return errorUrl;
	}
	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	public String getFailedUrl() {
		return failedUrl;
	}
	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}
	public Long getPaymentRequestId() {
		return paymentRequestId;
	}
	public void setPaymentRequestId(Long paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentRequestToken() {
		return paymentRequestToken;
	}
	public void setPaymentRequestToken(String paymentRequestToken) {
		this.paymentRequestToken = paymentRequestToken;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Payment [id=" + id + ", paymentUrl=" + paymentUrl + ", paymentId=" + paymentId + ", paymentRequestId="
				+ paymentRequestId + ", message=" + message + ", paymentRequestToken=" + paymentRequestToken + "]";
	}
	
}
