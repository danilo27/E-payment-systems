package central.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {
	@Id
	@GeneratedValue
    @Column(name = "ID")
    private Long id;
	
	public Transaction(){}
	
}
