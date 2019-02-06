package central.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PAYMENT_TYPE")
public class PaymentType implements Serializable{
	
	@Id
	@GeneratedValue
    @Column(name = "ID")
    private Long id;
	
	@Column(unique = true)
	private String name;
	
	private String label;
	
	private String imageUrl;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	private List<PaymentTypeField> fields = new ArrayList<>();
	
 	//@ManyToMany(mappedBy = "supportedPayments", fetch = FetchType.EAGER)
	//private List<Merchant> merchants = new ArrayList<>();
	
	public PaymentType(){}

	public PaymentType(String name, String label, String imageUrl, List<PaymentTypeField> fields) {
		super();
		this.name = name;
		this.label = label;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	 

	 
	
	
}
