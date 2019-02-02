package acq.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Cart {
	@Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
	
	private Double totalPrice;
	private HashMap<String, String> itemDetails = new HashMap<String, String>();
 
	private String token;
	public Cart(){}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public HashMap<String, String> getItemDetails() {
		return itemDetails;
	}
	public void setItemDetails(HashMap<String, String> itemDetails) {
		this.itemDetails = itemDetails;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", totalPrice=" + totalPrice + ", itemDetails=" + itemDetails + ", token=" + token
				+ "]";
	}
	
	
}
