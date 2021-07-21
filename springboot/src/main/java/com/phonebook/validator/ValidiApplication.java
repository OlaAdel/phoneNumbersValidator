package com.phonebook.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.phonebook.validator.service.CustomerService;


@SpringBootApplication
public class ValidiApplication {
		
	@Autowired
	CustomerService customerService;
		
	public static void main(String[] args) {
		
		SpringApplication.run(ValidiApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			customerService.ValidateDatabase();
		};
	}
	
	
}
