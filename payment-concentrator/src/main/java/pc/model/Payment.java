package pc.model;

public class Payment {
	private String paymentUrl;
	private int paymentId;
	private Long paymentRequestId;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	@Override
	public String toString() {
		return "Payment [paymentUrl=" + paymentUrl + ", paymentId=" + paymentId + ", paymentRequestId="
				+ paymentRequestId + ", message=" + message + "]";
	}
	
}
