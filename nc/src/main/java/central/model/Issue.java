package central.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "ISSUE")
public class Issue {
	
	@Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
	@JsonBackReference
    private Magazine magazine;
	
	@Column(name = "DATE", nullable = false)
	private String date;
	
	@Column(name = "PRICE", nullable = true)
	private Double price;
	
	public Issue(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	 
	 
}
