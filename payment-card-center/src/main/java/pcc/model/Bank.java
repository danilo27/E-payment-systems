package pcc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bank {
	@Id
	@GeneratedValue 
	private Long id;

	private String iin;

	private String url;

	public Bank() {}
	public Bank(String iin, String url) {
		this.iin = iin;
		this.url = url;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIin() {
		return iin;
	}
	public void setIin(String iin) {
		this.iin = iin;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
