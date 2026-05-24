package com.example.demo;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	private final ProductDao productDao;
	public DemoApplication(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Bean @Profile("dev")
	public ApplicationRunner runner(){
		return args -> {
			Product p1 = new Product("Guitor", "powerful Guitor", 2000);
			Product p2 = new Product("Piano", "powerful Piono", 6000);
			Product p3 = new Product("Violin", "powerful Violin", 1000);
			productDao.save(p1).subscribe();
			productDao.save(p2).subscribe();
			productDao.save(p3).subscribe();

			// Chain them into a single Flux and subscribe properly
//			Flux.just(p1, p2, p3)
//					.flatMap(productDao::save)
//					.subscribe(
//							savedProduct -> System.out.println("Saved: " + savedProduct.getName()),
//							error -> System.err.println("Error saving data: " + error.getMessage()),
//							() -> System.out.println("Database initialization complete!")
//					);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}