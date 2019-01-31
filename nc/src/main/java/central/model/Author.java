package central.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "Author")
//@DiscriminatorValue(RoleName.AUTHOR)
public class Author extends User {
	
	@Column
	private String autorovoObelezje;
	/*
	@Autowired
	private RoleRepository roleRepository;
	
	*/
	public Author(){
		//super.setRole(roleRepository.findByName(RoleName.AUTHOR));
	}

	public String getAutorovoObelezje() {
		return autorovoObelezje;
	}

	public void setAutorovoObelezje(String autorovoObelezje) {
		this.autorovoObelezje = autorovoObelezje;
	}
	
	

}
