package iss.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_URL_AND_ID")
public class PaymentURLandID implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "PAYMENT_URL_AND_ID")
	private Long id;
	
	@Column(name = "PAYMENT_URL")
	private String paymentUrl;
	
	@Column(name = "PAYMENT_ID")
	private Integer paymentId;
}


