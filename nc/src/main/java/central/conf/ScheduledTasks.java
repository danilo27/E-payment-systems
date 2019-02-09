package central.conf;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import central.model.Cart;
import central.repository.CartRepository;


@Component
public class ScheduledTasks {

	@Autowired
	private CartRepository cartRepository;
	
    @Scheduled(fixedRate = 36000000)	//svakih sat vremena
    public void reportCurrentTime(){
    	
		//System.out.println("SCHEDUELED");
		List<Cart> carts = cartRepository.findByStatus("inProgress");
		
		for (Cart cart: carts){
			Date now = new Date();
			Long differenceInMiliseconds = now.getTime() - cart.getMerchantTimestamp().getTime();	
			if (differenceInMiliseconds >= 18000000){
				cart.setStatus("expired");
				cartRepository.save(cart);
			}
			
		}
    }
}
