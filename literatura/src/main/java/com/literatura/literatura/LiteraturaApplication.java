package com.literatura.literatura;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.literatura.literatura", "console", "service"})
@EntityScan("model")
@EnableJpaRepositories("repository")
public class LiteraturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}
}
