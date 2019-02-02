package central.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import central.repository.RoleRepository;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="role", discriminatorType = DiscriminatorType.STRING)
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String firstName;

	@Column
	private String lastName;
	
	@ManyToOne(optional = false)
	private Role role;
 
	
	@ManyToMany
    private List<UserItem> userItems = new ArrayList<UserItem>();
 
	/*
	@Autowired
	private RoleRepository roleRepository;
	*/
	public User() {
		//this.role = roleRepository.findByName(RoleName.USER);
	}

 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 
	public List<UserItem> getUserItems() {
		return userItems;
	}

	public void setUserItems(List<UserItem> userItems) {
		this.userItems = userItems;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}
