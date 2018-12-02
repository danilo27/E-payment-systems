package pc.dto;

public class PaymentRequestDto {

	private String amount;
	private String currency;
	private String payee;
	
	public PaymentRequestDto(){
		
	}

	public String getPayee() {
		return payee;
	}



	public void setPayee(String payee) {
		this.payee = payee;
	}



	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
