package central.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class SupportedPayments {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String imageUrl;
	/*
	@ManyToMany(mappedBy = "supportedPayments")
	private Set<Merchant> merchants = new HashSet<Merchant>();
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


/*
	public Set<Merchant> getMerchants() {
		return merchants;
	}

	public void setMerchants(Set<Merchant> merchants) {
		this.merchants = merchants;
	}*/

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

	@Override
	public String toString() {
		return "SupportedPayments [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + "]";
	}
	
	
}
