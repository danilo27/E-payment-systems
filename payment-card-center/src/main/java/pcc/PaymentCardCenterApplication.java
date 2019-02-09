package pcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PaymentCardCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentCardCenterApplication.class, args);
	}
}
