package com.example.saecurityapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class SecurityAppApplication {
	public static final Logger LOGGER = LoggerFactory.getLogger(SecurityAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SecurityAppApplication.class, args);
	}

}
