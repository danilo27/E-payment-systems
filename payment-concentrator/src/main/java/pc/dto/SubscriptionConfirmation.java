package pc.dto;

public class SubscriptionConfirmation {

	private String token;

	public SubscriptionConfirmation(){
		
	}
	
	public SubscriptionConfirmation(String token){
		this.token = token;
	}
	
	public String getToken() {
		// TODO Auto-generated method stub
		return token;
	}

	public void setToken(String token) {
		// TODO Auto-generated method stub
		this.token = token;
	}

}
