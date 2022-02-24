package com.trick.email.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.trick.email.api.domain.model")
@EnableJpaRepositories(basePackages = "com.trick.email.api.domain.repository")
public class TrickEmailApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrickEmailApiApplication.class, args);
	}

}
