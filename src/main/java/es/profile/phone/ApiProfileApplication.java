package es.profile.phone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication( scanBasePackages = { "es.profile.phone" } )
public class ApiProfileApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiProfileApplication.class, args);
	}
}
