package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Role;
import central.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(RoleName author);

}
