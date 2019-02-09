package acq.conf;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import acq.model.Cart;
import acq.model.Payment;
import acq.repositories.PaymentRepository;
import acq.repositories.PaymentRequestRepository;
import acq.services.PaymentService;;


@Component
public class ScheduledTasks {

	@Autowired
	private PaymentService paymentService;
	
    @Scheduled(fixedRate = 36000000)	//svakih sat vremena
    public void reportCurrentTime(){
    	
		//System.out.println("SCHEDUELED");
		List<Payment> payments = paymentService.findByMessage("inProgress");
		
		for (Payment payment: payments){
			Date now = new Date();
			Long differenceInMiliseconds = now.getTime() - payment.getMerchantTimestamp().getTime();	
			if (differenceInMiliseconds >= 18000000){
				payment.setMessage("expired");
				paymentService.save(payment);
			}
			
		}
    }
}
