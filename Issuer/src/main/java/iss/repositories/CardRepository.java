package iss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iss.model.Account;
import iss.model.Card;
 

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{
 

}