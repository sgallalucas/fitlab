package sgallalucas.fitlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FitlabApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitlabApplication.class, args);
	}

}
