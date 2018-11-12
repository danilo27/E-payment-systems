package acq.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACQUIRER_ORDER")
public class AcquirerOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ACQUIRER_ORDER_ID")
	private Long id;

	@Column(name = "ACQUIRER_TIMESTAMP")
	private Timestamp timestamp;

	//TODO card details
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AcquirerOrder(Long id, Timestamp timestamp) {
		super();
		this.id = id;
		this.timestamp = timestamp;
	}
	
	
}
