package pc.dto;

public class SubscriptionConfirmation {

	private String token;
	private String paymentTypeName;

	public String getToken() {
		// TODO Auto-generated method stub
		return token;
	}

	public void setToken(String token) {
		// TODO Auto-generated method stub
		this.token = token;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

}
