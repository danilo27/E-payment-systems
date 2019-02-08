package pc.dto;

import org.springframework.http.ResponseEntity;

public class PaymentConfirmationDto {

	private String paymentId;
	private String payerId;
	ResponseEntity<?> response;
	
	public ResponseEntity<?> getResponse() {
		return response;
	}

	public void setResponse(ResponseEntity<?> response) {
		this.response = response;
	}

	public PaymentConfirmationDto(){
		
	}
	

	public PaymentConfirmationDto(String paymentId, String payerId) {
		this.paymentId = paymentId;
		this.payerId = payerId;
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
