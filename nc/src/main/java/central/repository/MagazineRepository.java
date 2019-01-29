package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, String>{
	public Magazine findOneByIssn(String issn);
}
