package central.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Author")
//@DiscriminatorValue(RoleName.AUTHOR)
public class Author extends User {
	
	public Author(){
	}

	
	

}
