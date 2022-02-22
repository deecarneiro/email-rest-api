package com.trick.email.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.trick.email.domain.model.Email;

@SpringBootApplication
@EntityScan(basePackages = "com.trick.email.domain.model")
public class TrickEmailApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrickEmailApiApplication.class, args);
	}

}
