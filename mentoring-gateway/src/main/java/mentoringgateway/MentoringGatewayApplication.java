package mentoringgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MentoringGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentoringGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(p -> p.path("/api/employees")
						.filters(f -> f.circuitBreaker(c -> c.setName("employeeCircuitBreaker").setFallbackUri("/api/dummy-employees")))
						.uri("http://localhost:8081"))
				.route(p -> p.path("/api/roles").uri("http://localhost:8084"))
				.build();
	}
}
