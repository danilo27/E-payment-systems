package central.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription {
	
	@Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;
	
	@Column(name = "PRICE", nullable = false)
	private Double price; 
	
	@Column(name = "TYPE", nullable = false)
	private String typeOfSubscription; 
	
	@Column(name = "VALID_UNTIL", nullable = false)
    private Date date;

    @Column(name = "PAID", nullable = false)
    private Boolean paid;

    @Column(name = "CANCELLED", nullable = true)
    private Boolean cancelled;
	
	public Subscription(){}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTypeOfSubscription() {
		return typeOfSubscription;
	}

	public void setTypeOfSubscription(String typeOfSubscription) {
		this.typeOfSubscription = typeOfSubscription;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	
}
