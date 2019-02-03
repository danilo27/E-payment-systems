package iss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iss.model.Card;
import iss.model.IssueOrder;

@Repository
public interface IssueOrderRepository extends JpaRepository<IssueOrder, Long>{

}
