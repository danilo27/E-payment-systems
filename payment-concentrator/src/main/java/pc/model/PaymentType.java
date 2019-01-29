package pc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_TYPE")
public class PaymentType {
	@Id
	@GeneratedValue
    @Column(name = "ID")
    private Long id;
	
	private String name;
	private String imageUrl;
	@OneToMany(fetch = FetchType.EAGER)
	private List<PaymentTypeField> fields = new ArrayList<>();
	
	public PaymentType(){}

	public PaymentType(String name, String imageUrl, List<PaymentTypeField> fields) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.fields = fields;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<PaymentTypeField> getFields() {
		return fields;
	}
	public void setFields(List<PaymentTypeField> fields) {
		this.fields = fields;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
