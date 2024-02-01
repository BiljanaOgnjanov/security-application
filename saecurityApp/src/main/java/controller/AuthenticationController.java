package controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.AuthenticationRequest;
import dto.AuthenticationResponse;
import dto.RegisterRequest;
import model.Accommodation;
import service.AuthenticationService;
import service.ValidationService;

@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;
	@Autowired
	private ValidationService validaiontService;
	
	@CrossOrigin
	@PostMapping(consumes = "application/json", value = "/registerGuest")
	public ResponseEntity<AuthenticationResponse> registerGuest(@RequestBody RegisterRequest request) {
		if(validaiontService.validate(request)) {
			authService.registerGuest(request);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@CrossOrigin
	@PostMapping(consumes = "application/json", value = "/registerHost")
	public ResponseEntity<AuthenticationResponse> registerHost(@RequestBody RegisterRequest request) {
		if(validaiontService.validate(request)) {
			authService.registerHost(request);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@CrossOrigin
	@PostMapping(consumes = "application/json", value = "/login")
	public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
		if(validaiontService.validate(request)) {
			return new ResponseEntity<>(authService.authenticate(request), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@CrossOrigin
	@GetMapping(value = "/activate/{id}")
    public ResponseEntity<Object> activateUser(@PathVariable UUID id) {
		return new ResponseEntity<>(authService.activate(id), HttpStatus.OK);
	}


}
