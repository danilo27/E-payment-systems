package central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NcApplication {

	public static void main(String[] args) {
		SpringApplication.run(NcApplication.class, args);
	}

}

