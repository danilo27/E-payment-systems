/*package pc.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
           .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
           .allowedOrigins("http://localhost:8080","http://localhost:4200",
        		   "http://localhost:8084","http://localhost:4204"
        		   );;
      
            registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080","http://localhost:4200",
                		"http://localhost:8084","http://localhost:4204");
            
            registry.addMapping("/api/nc/transaction/proceedToPc").allowedOrigins("http://localhost:8080");
            registry.addMapping("/api/nc/transaction/proceedToPc").allowedOrigins("http://localhost:4200");
    }
}*/