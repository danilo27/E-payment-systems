package iss.dto;

import iss.model.Card;
import iss.model.PaymentRequest;

import java.util.Date;

import iss.model.enums.TransactionResult;

public class AcqToPccDto {
	private Long acquirer_order_id;
	private Date acquirer_timestamp;
	private Long issuer_order_id;
	private Date issuer_timestamp;
	private String acq_url;
	private String iss_url;
	private Card card;
	private PaymentRequest pr;
	private TransactionResult transactionResult;
	
	public AcqToPccDto(){}
	
	public TransactionResult getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(TransactionResult transactionResult) {
		this.transactionResult = transactionResult;
	}

	public String getAcq_url() {
		return acq_url;
	}

	public void setAcq_url(String acq_url) {
		this.acq_url = acq_url;
	}

	public PaymentRequest getPr() {
		return pr;
	}

	public void setPr(PaymentRequest pr) {
		this.pr = pr;
	}

	public String getIss_url() {
		return iss_url;
	}

	public void setIss_url(String iss_url) {
		this.iss_url = iss_url;
	}

	public Long getAcquirer_order_id() {
		return acquirer_order_id;
	}
	public void setAcquirer_order_id(Long acquirer_order_id) {
		this.acquirer_order_id = acquirer_order_id;
	}
	 
	public Date getAcquirer_timestamp() {
		return acquirer_timestamp;
	}

	public void setAcquirer_timestamp(Date acquirer_timestamp) {
		this.acquirer_timestamp = acquirer_timestamp;
	}

	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}

	public Long getIssuer_order_id() {
		return issuer_order_id;
	}

	public void setIssuer_order_id(Long issuer_order_id) {
		this.issuer_order_id = issuer_order_id;
	}

	public Date getIssuer_timestamp() {
		return issuer_timestamp;
	}

	public void setIssuer_timestamp(Date issuer_timestamp) {
		this.issuer_timestamp = issuer_timestamp;
	}
	
	
	
}
