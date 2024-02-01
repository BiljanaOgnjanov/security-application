package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import config.JwtService;
import dto.AuthenticationRequest;
import dto.AuthenticationResponse;
import dto.ChangeAccountInfoRequest;
import dto.ChangePasswordRequest;
import model.AppUser;
import repo.AppUserRepo;

@Service
public class AppUserService {
	@Autowired
	private AppUserRepo appUserRepo;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationService authService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AuthenticationResponse changeAccountInfo(ChangeAccountInfoRequest request, String extractUsername) {
		AppUser user = appUserRepo.findByUsername(extractUsername);
		user.setAdress(request.getAdress());
		user.setEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUsername(request.getUsername());
		appUserRepo.save(user);
		AuthenticationResponse resp = new AuthenticationResponse();
		resp.setToken(jwtService.generateToken(user.getRole().name(), user));
		return resp;
	}


	public ChangeAccountInfoRequest getAccountInfo(String extractUsername) {
		AppUser user = appUserRepo.findByUsername(extractUsername);
		ChangeAccountInfoRequest response = new ChangeAccountInfoRequest();
		response.setAdress(user.getAdress());
		response.setEmail(user.getEmail());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setUsername(user.getUsername());
		return response;
	}


	public Object changePassword(ChangePasswordRequest request, String extractUsername) {
		AuthenticationRequest req = new AuthenticationRequest();
		req.setPassword(request.getOldPassword());
		req.setUsername(extractUsername);
		AuthenticationResponse resp = authService.authenticate(req);
		AppUser user = appUserRepo.findByUsername(extractUsername);
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		appUserRepo.save(user);
		return null;
	}
}