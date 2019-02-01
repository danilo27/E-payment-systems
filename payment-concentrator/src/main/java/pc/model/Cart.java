package pc.model;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Cart {
	private Double totalPrice;
	private HashMap<String, String> itemDetails = new HashMap<String, String>();
	@Id	
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
	
	
}
