package acq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AcquirerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcquirerApplication.class, args);
	}
}
