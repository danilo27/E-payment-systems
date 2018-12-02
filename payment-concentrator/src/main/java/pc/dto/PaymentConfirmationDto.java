package pc.dto;

public class PaymentConfirmationDto {

	private String paymentId;
	private String payerId;
	
	public PaymentConfirmationDto(){
		
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	
	
}
