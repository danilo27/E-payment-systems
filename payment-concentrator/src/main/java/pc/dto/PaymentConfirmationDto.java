package pc.dto;

import org.springframework.http.ResponseEntity;

public class PaymentConfirmationDto {

	private String paymentId;
	private String payerId;
	private String paymentTypeName;
	ResponseEntity<?> response;
	
	public ResponseEntity<?> getResponse() {
		return response;
	}

	public void setResponse(ResponseEntity<?> response) {
		this.response = response;
	}

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

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	
	
}
