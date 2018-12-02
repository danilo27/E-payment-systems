package acq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acq.model.Account;
import acq.model.Card;
 

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{
 

}