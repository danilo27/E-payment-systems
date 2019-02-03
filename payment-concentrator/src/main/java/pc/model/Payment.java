package pc.model;

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
	private int paymentId;
	private Long paymentRequestId;
	private String message;
	private String paymentRequestToken;

	public Payment() {
	}

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

	public String getPaymentRequestToken() {
		return paymentRequestToken;
	}

	public void setPaymentRequestToken(String paymentRequestToken) {
		this.paymentRequestToken = paymentRequestToken;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", paymentUrl=" + paymentUrl + ", paymentId=" + paymentId + ", paymentRequestId="
				+ paymentRequestId + ", message=" + message + ", paymentRequestToken=" + paymentRequestToken + "]";
	}

}
