package service;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import dto.AccommodationImageDTO;
import dto.AddAccommodationDTO;
import dto.AmenityDTO;
import dto.AuthenticationRequest;
import dto.ChangeAccountInfoRequest;
import dto.ChangePasswordRequest;
import dto.FreeTimeSlotDTO;
import dto.RegisterRequest;
import dto.SearchDTO;


@Service
public class ValidationService {
	
	public boolean validate(RegisterRequest request) {
		String password_regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		/*
		^                 # start-of-string
		(?=.*[0-9])       # a digit must occur at least once
		(?=.*[a-z])       # a lower case letter must occur at least once
		(?=.*[A-Z])       # an upper case letter must occur at least once
		(?=.*[@#$%^&+=])  # a special character must occur at least once (@, #, $, %, ^, &, +, =)
		(?=\S+$)          # no whitespace allowed in the entire string
		.{8,}             # anything, at least eight places though
		$                 # end-of-string
		*/
		
		Pattern email_regex = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		
		if (request.getAdress().isBlank() || request.getAdress().equals(null)) {
			System.out.println("getAdress");
			return false;
		}
		if (request.getFirstName().isBlank() || request.getFirstName().equals(null)) {
			System.out.println("getFirstName");
			return false;
		}
		if (request.getLastName().isBlank() || request.getLastName().equals(null)) {
			System.out.println("getLastName");
			return false;
		}
		if (request.getUsername().isBlank() || request.getUsername().equals(null)) {
			System.out.println("getUsername");
			return false;
		}
		if (request.getPassword().isBlank() || request.getPassword().equals(null) || !request.getPassword().matches(password_regex)) {
			System.out.println(request.getPassword().matches(password_regex));
			return false;
		}
		if (request.getEmail().isBlank() || request.getEmail().equals(null) || !email_regex.matcher(request.getEmail()).matches()) {
			System.out.println("getEmail");
			return false;
		}
		return true;
	}
	public boolean validate(AddAccommodationDTO request) {	
		if (request.getLocation().isBlank() || request.getLocation().equals(null)) {
			System.out.println("getLocation");
			return false;
		}
		if (request.getName().isBlank() || request.getName().equals(null)) {
			System.out.println("getName");
			return false;
		}
		if (request.getMaxGuests()<= 0 || request.getMaxGuests().equals(null)) {
			System.out.println("getMaxGuests");
			return false;
		}
		if (request.getMinGuests() <= 0 || request.getMinGuests().equals(null)) {
			System.out.println("getMinGuests");
			return false;
		}
		if (request.getMinGuests() > request.getMaxGuests()) {
			System.out.println("gues number mismatch");
			return false;
		}
		for (AmenityDTO amenity : request.getAmenities()) {
			if(amenity.getAmenity().isBlank() || amenity.getAmenity().equals(null)) {
				return false;
			}
		}
		for (AccommodationImageDTO image : request.getAccommodationImages()) {
			if(image.getImg().isBlank() || image.getImg().equals(null)) {
				return false;
			}
		}

		return true;
	}
	public boolean validate(AuthenticationRequest request) {
		if (request.getUsername().isBlank() || request.getUsername().equals(null)) {
			System.out.println("getUsername");
			return false;
		}
		if (request.getPassword().isBlank() || request.getPassword().equals(null)) {
			System.out.println("getPassword");
			return false;
		}

		return true;
	}
	public boolean validate(ChangeAccountInfoRequest request) {	
		Pattern email_regex = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		
		if (request.getAdress().isBlank() || request.getAdress().equals(null)) {
			System.out.println("getAdress");
			return false;
		}
		if (request.getFirstName().isBlank() || request.getFirstName().equals(null)) {
			System.out.println("getFirstName");
			return false;
		}
		if (request.getLastName().isBlank() || request.getLastName().equals(null)) {
			System.out.println("getLastName");
			return false;
		}
		if (request.getUsername().isBlank() || request.getUsername().equals(null)) {
			System.out.println("getUsername");
			return false;
		}
		if (request.getEmail().isBlank() || request.getEmail().equals(null) || !email_regex.matcher(request.getEmail()).matches()) {
			System.out.println("getEmail");
			return false;
		}
		return true;
	}
	public boolean validate(FreeTimeSlotDTO request) {	
		if (request.getPrice() <= 0 || request.getPrice().equals(null)) {
			System.out.println("getPrice");
			return false;
		}
		if (request.getStartDate().equals(null) || request.getStartDate().before(new Date())) {
			System.out.println("getStartDate");
			return false;
		}
		if (request.getEndDate().equals(null) || request.getEndDate().before(new Date())) {
			System.out.println("getEndDate");
			return false;
		}
		if (request.getStartDate().before(request.getEndDate())) {
			System.out.println("dates mismatch");
			return false;
		}
		return true;
	}
	public boolean validate(SearchDTO request) {	
		if (request.getBeginDate().after(request.getEndDate())) {
			System.out.println("date mismatch");
			return false;
		}
		if (request.getGuests() == 0 || request.getGuests().equals(null)) {
			System.out.println("getStartDate");
			return false;
		}
		if (request.getLocation().equals(null)) {
			System.out.println("getLocation");
			return false;
		}
		if (request.getMaxPrice() < request.getMinPrice()) {
			System.out.println("price mismatch");
			return false;
		}
		return true;
	}

	public boolean validate(ChangePasswordRequest request) {
		String password_regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		/*
		^                 # start-of-string
		(?=.*[0-9])       # a digit must occur at least once
		(?=.*[a-z])       # a lower case letter must occur at least once
		(?=.*[A-Z])       # an upper case letter must occur at least once
		(?=.*[@#$%^&+=])  # a special character must occur at least once (@, #, $, %, ^, &, +, =)
		(?=\S+$)          # no whitespace allowed in the entire string
		.{8,}             # anything, at least eight places though
		$                 # end-of-string
		*/
		
		if (request.getNewPassword().isBlank() || request.getNewPassword().equals(null) || !request.getNewPassword().matches(password_regex)) {
			System.out.println(request.getNewPassword().matches(password_regex));
			return false;
		}
		return true;
	}
}
