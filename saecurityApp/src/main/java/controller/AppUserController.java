package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import config.JwtService;
import dto.AuthenticationResponse;
import dto.ChangeAccountInfoRequest;
import service.AppUserService;

@RestController
@RequestMapping(value = "api/user")
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private JwtService jwtService;
	
	@CrossOrigin
	@PostMapping(value = "/changeAcoountInfo")
	@PreAuthorize("hasAuthority('change_account_info')")
    public ResponseEntity<AuthenticationResponse> changeAcoountInfo(@RequestBody ChangeAccountInfoRequest request, @RequestHeader (name="Authorization") String token) {
		return new ResponseEntity<>(appUserService.changeAccountInfo(request, jwtService.extractUsername(token.substring(7))), HttpStatus.OK);
	}
	@CrossOrigin
	@GetMapping(value = "/acoountInfo")
	@PreAuthorize("hasAuthority('get_account_info')")
    public ResponseEntity<ChangeAccountInfoRequest> getAcoountInfo(@RequestHeader (name="Authorization") String token) {
		return new ResponseEntity<>(appUserService.getAccountInfo(jwtService.extractUsername(token.substring(7))), HttpStatus.OK);
	}
	@CrossOrigin
	@GetMapping(value = "/deleteAccount")
	@PreAuthorize("hasAuthority('delete_account')")
    public ResponseEntity<String> deleteAccount() {
		return new ResponseEntity<>("deleteAccount GOOD", HttpStatus.OK);
	}
	
}
