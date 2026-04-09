package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.bean.Employee;

@Configuration
public class AppConfig { // java config
	@Bean
	public Employee employee() {
		Employee em = new Employee();
		em.setFirstName("Dark");
		em.setLastName("Orion");
		return em;
	}
}
