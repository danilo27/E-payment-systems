package central.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;



@Entity
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "fieldName"})})
public class PaymentTypeField implements Serializable{
	@EmbeddedId
	private KeyClass id;
	
	@MapsId("Name")
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="name", insertable = false, updatable = false, referencedColumnName="name") 
    private PaymentType paymentType;
 
	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	String label;

	String type;

	public PaymentTypeField() {}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

 

	public KeyClass getId() {
		return id;
	}

	public void setId(KeyClass id) {
		this.id = id;
	}
 

	public PaymentTypeField( PaymentType paymentType, String label, String type) {
		super();
		  
		this.paymentType = paymentType;
		this.label = label;
		this.type = type;
	}

	 

	 
 

	 

	 
	
	
}