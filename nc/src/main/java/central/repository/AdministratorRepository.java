package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import central.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	
}
