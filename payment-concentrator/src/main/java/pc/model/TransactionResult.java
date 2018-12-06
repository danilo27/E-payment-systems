package pc.model;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.http.ResponseEntity;

public class TransactionResult {
	
	private String redirectUrl;
	private String successMessage;
	private ResponseEntity<?> response;
	
	public ResponseEntity<?> getResponse() {
		return response;
	}

	public void setResponse(ResponseEntity<?> response) {
		this.response = response;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl){
		this.redirectUrl = redirectUrl;
	}
	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

 

 
	public TransactionResult(){}
}
