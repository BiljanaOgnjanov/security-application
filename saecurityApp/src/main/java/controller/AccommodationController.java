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
import dto.AddAccommodationDTO;
import model.Accommodation;
import service.AccommodationService;

@RestController
@RequestMapping(value = "api/accommodation")
public class AccommodationController {
	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private JwtService jwtService;
	
	@CrossOrigin
	@GetMapping(value = "/public/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable UUID id) {
		return new ResponseEntity<>(accommodationService.getById(id), HttpStatus.OK);
	}
	@CrossOrigin
	@PostMapping(value = "/addAccommodation")
	@PreAuthorize("hasAuthority('add_accommodation')")
    public ResponseEntity<Object> addAccommodation(@RequestBody AddAccommodationDTO request, @RequestHeader (name="Authorization") String token) {
		return new ResponseEntity<>(accommodationService.addAccommodation(request, jwtService.extractUsername(token.substring(7))), HttpStatus.CREATED);
	}

	
}
