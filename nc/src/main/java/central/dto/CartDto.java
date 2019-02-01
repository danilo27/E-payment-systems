package central.dto;

import java.util.HashMap;

public class CartDto {
	private Double totalPrice;
	private HashMap<String, String> itemDetails = new HashMap<String, String>();
	
	public CartDto(){}
	
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
	
	
}
