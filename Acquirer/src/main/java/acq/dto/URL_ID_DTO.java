package acq.dto;

import acq.model.PaymentRequest;

public class URL_ID_DTO {
	private String paymentUrl;
	private int paymentId;
	private PaymentRequest paymentRequest;
	public PaymentRequest getPaymentRequest() {
		return paymentRequest;
	}
	public void setPaymentRequest(PaymentRequest paymentRequest) {
		this.paymentRequest = paymentRequest;
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
	public URL_ID_DTO(String paymentUrl, int paymentId) {
		super();
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
	}
	public URL_ID_DTO(){}
}
