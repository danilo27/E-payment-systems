package pc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_TYPE_FIELD")
public class PaymentTypeField {
	@Id
	@Column(name = "ID")
	String label;

	String type;

	public PaymentTypeField() {
	}

	public PaymentTypeField(String label, String type) {
		super();
		this.label = label;
		this.type = type;
	}

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

}
