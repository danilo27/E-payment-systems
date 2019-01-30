package central.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Issue;
 
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
	public List<Issue> findByMagazineIssn(String issn);
}
