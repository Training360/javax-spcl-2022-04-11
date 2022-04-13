package frontendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FrontendServiceApplication
{

	public static void main(String[] args) {
		SpringApplication.run(FrontendServiceApplication.class, args);
	}

}
