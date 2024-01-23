package com.example.saecurityapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import dto.RegisterRequest;
import service.AuthenticationService;

@EnableScheduling
@RestController
@EnableJpaRepositories("repo")
@EntityScan("model")
@ComponentScan({"controller"})
@ComponentScan({"service"})
@ComponentScan({"config"})
@SpringBootApplication
public class SecurityAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecurityAppApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService authService) {
		return args ->{
			RegisterRequest user1 = new RegisterRequest();
			user1.setFirstName("Guest");
			user1.setLastName("User");
			user1.setAdress("Boston");
			user1.setEmail("guest@user.com");
			user1.setUsername("guest");
			user1.setPassword("guest");
			String guestToken = authService.registerGuest(user1).getToken();
			
			RegisterRequest user2 = new RegisterRequest();
			user2.setFirstName("Host");
			user2.setLastName("User");
			user2.setAdress("Boston");
			user2.setEmail("host@user.com");
			user2.setUsername("host");
			user2.setPassword("host");
			String hostToken = authService.registerHost(user2).getToken();

			System.out.println("GUEST TOKEN: " + guestToken);
			System.out.println("HOST TOKEN: " + hostToken);
			
		};
		}

}
