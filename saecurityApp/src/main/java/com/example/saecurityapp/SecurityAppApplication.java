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

import dto.AddAccommodationDTO;
import dto.AmenityDTO;
import dto.RegisterRequest;
import model.Accommodation;
import model.PriceCalculation;
import service.AccommodationService;
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
	public CommandLineRunner commandLineRunner(AuthenticationService authService, AccommodationService accommodationService) {
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

			AddAccommodationDTO accommo1 = new AddAccommodationDTO();
			AddAccommodationDTO accommo2 = new AddAccommodationDTO();
			
			AmenityDTO wifi = new AmenityDTO();
			wifi.setAmenity("Wi-Fi");
			AmenityDTO washingMachine = new AmenityDTO();
			washingMachine.setAmenity("Washing Machine");
			
			accommo1.setLocation("Budapest");
			accommo1.setMaxGuests(5);
			accommo1.setMinGuests(3);
			accommo1.setName("Arpad Residence");
			accommo1.setPriceCalculation(PriceCalculation.PER_GUEST_PER_NIGHT);
			accommo1.getAmenities().add(washingMachine);
			accommo1.getAmenities().add(wifi);
			
			accommo2.setLocation("Belgrade");
			accommo2.setMaxGuests(4);
			accommo2.setMinGuests(2);
			accommo2.setName("Nemanjic Residence");
			accommo2.setPriceCalculation(PriceCalculation.PER_ACCOMMODATION_PER_NIGHT);
			accommo2.getAmenities().add(wifi);
			
			Accommodation budapest = (Accommodation) accommodationService.addAccommodation(accommo1, user2.getUsername());
			Accommodation belgrade = (Accommodation) accommodationService.addAccommodation(accommo2, user2.getUsername());
			
			System.out.println("GUEST TOKEN: " + guestToken);
			System.out.println("HOST TOKEN: " + hostToken);
			System.out.println("BUDAPEST ID: " + budapest.getId());
			System.out.println("BELGRADE ID: " + belgrade.getId());
			
		};
		}

}
