package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.AuthenticationRequest;
import dto.AuthenticationResponse;
import dto.RegisterRequest;
import service.AuthenticationService;

@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;
	
	@CrossOrigin
	@PostMapping(consumes = "application/json", value = "/registerGuest")
	public ResponseEntity<AuthenticationResponse> registerGuest(@RequestBody RegisterRequest request) {
		return new ResponseEntity<>(authService.registerGuest(request), HttpStatus.CREATED);
	}
	@CrossOrigin
	@PostMapping(consumes = "application/json", value = "/registerHost")
	public ResponseEntity<AuthenticationResponse> registerHost(@RequestBody RegisterRequest request) {
		return new ResponseEntity<>(authService.registerHost(request), HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PostMapping(consumes = "application/json", value = "/login")
	public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
		return new ResponseEntity<>(authService.authenticate(request), HttpStatus.OK);
	}

}
