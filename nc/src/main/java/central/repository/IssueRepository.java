package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Issue;
 
@Repository
public interface IssueRepository extends JpaRepository<Issue, String> {

}
