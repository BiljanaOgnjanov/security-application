package controller;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import config.JwtService;
import dto.AccommodationSeachResultDTO;
import dto.AddAccommodationDTO;
import dto.SearchDTO;
import model.Accommodation;
import service.AccommodationService;
import service.ValidationService;

@RestController
@RequestMapping(value = "api/accommodation")
public class AccommodationController {
	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private ValidationService validaiontService;
	
	@CrossOrigin
	@GetMapping(value = "/public/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable UUID id) {
		return new ResponseEntity<>(accommodationService.getById(id), HttpStatus.OK);
	}
	@CrossOrigin
	@PostMapping(value = "/public/search")
    public ResponseEntity<Set<AccommodationSeachResultDTO>> searchAccommodation(@RequestBody SearchDTO request) {
		if(validaiontService.validate(request)) {
			return new ResponseEntity<>(accommodationService.searchAccommodtaion(request), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	@CrossOrigin
	@PostMapping(value = "/addAccommodation")
	@PreAuthorize("hasAuthority('add_accommodation')")
    public ResponseEntity<Object> addAccommodation(@RequestBody AddAccommodationDTO request, @RequestHeader (name="Authorization") String token) {
		if(validaiontService.validate(request)) {
			return new ResponseEntity<>(accommodationService.addAccommodation(request, jwtService.extractUsername(token.substring(7))), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@CrossOrigin
	@GetMapping(value = "/listMyAccommodations")
	@PreAuthorize("hasAuthority('list_my_accommodations')")
    public ResponseEntity<Set<Accommodation>> listMyAccommodations(@RequestHeader (name="Authorization") String token) {
		return new ResponseEntity<>(accommodationService.listForHost(jwtService.extractUsername(token.substring(7))), HttpStatus.OK);
	}
	
}
