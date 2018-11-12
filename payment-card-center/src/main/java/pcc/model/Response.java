package pcc.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import pcc.enums.TransactionResult;

@Entity
@Table(name = "RESPONSE")
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "RESPONSE_ID")
	private Long id;
	
	@Column(name = "ACQUIRER_ORDER_ID")
	private Integer acquirerOrderId;

	@Column(name = "ACQUIRER_TIMESTAMP")
	private Timestamp acquirerTimestamp;

	@Column(name = "ISSUER_ORDER_ID")
	private Integer issuerOrderId;

	@Column(name = "ISSUER_TIMESTAMP")
	private Timestamp issuerTimestamp;
	
	@Column(name = "TRANSACTION_RESULT")
	private TransactionResult result;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(Integer acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Timestamp getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Timestamp acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public Integer getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(Integer issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Timestamp getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(Timestamp issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}

	public TransactionResult getResult() {
		return result;
	}

	public void setResult(TransactionResult result) {
		this.result = result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
