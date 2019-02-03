package iss.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ISSUE_ORDER")
public class IssueOrder {
	@Id
	@GeneratedValue
	@Column(name = "ISSUER_ORDER_ID")
	private Long id;
	
	@Column(name = "ISSUER_TIMESTAMP")
	private Date issuer_timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getIssuer_timestamp() {
		return issuer_timestamp;
	}

	public void setIssuer_timestamp(Date issuer_timestamp) {
		this.issuer_timestamp = issuer_timestamp;
	}

	@Override
	public String toString() {
		return "IssueOrder [id=" + id + ", issuer_timestamp=" + issuer_timestamp + "]";
	}
	
	
}
