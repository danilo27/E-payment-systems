package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Issue;
import central.model.UserItem;

@Repository
public interface UserItemRepository extends JpaRepository<UserItem, Long> {

}
