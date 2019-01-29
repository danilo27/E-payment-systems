package central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

}
