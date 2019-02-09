package iss.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

 
import iss.model.Payment;
import iss.services.PaymentService;;


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
			Long differenceInMiliseconds = now.getTime() - payment.getAcquirerTimestamp().getTime();	
			if (differenceInMiliseconds >= 18000000){
				payment.setMessage("expired");
				paymentService.save(payment);
			}
			
		}
    }
}
