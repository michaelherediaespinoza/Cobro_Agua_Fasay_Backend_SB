package coac.fasay.cobroagua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CobroaguaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobroaguaApplication.class, args);
	}

}
