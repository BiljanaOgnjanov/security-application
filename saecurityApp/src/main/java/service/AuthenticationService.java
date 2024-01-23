package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import config.JwtService;
import dto.AuthenticationRequest;
import dto.AuthenticationResponse;
import dto.RegisterRequest;
import model.AppUser;
import model.Role;
import repo.AppUserRepo;

@Service
public class AuthenticationService {
	
	@Autowired
	private AppUserRepo appUserRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authManager;
	
	public AuthenticationResponse registerGuest(RegisterRequest request) {
		AppUser user = new AppUser();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setAdress(request.getAdress());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.GUEST);
		user.setUsername(request.getUsername());
		appUserRepo.save(user);
		String jwtToken = jwtService.generateToken(user.getRole().name(), user);
		AuthenticationResponse resp = new AuthenticationResponse();
		resp.setToken(jwtToken);
		return resp;
	}
	
	public AuthenticationResponse registerHost(RegisterRequest request) {
		AppUser user = new AppUser();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setAdress(request.getAdress());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.HOST);
		user.setUsername(request.getUsername());
		appUserRepo.save(user);
		String jwtToken = jwtService.generateToken(user.getRole().name(), user);
		AuthenticationResponse resp = new AuthenticationResponse();
		resp.setToken(jwtToken);
		return resp;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		AppUser user = appUserRepo.findByUsername(request.getUsername());
		System.out.println(user.getFirstName());
		String jwtToken = jwtService.generateToken(user.getRole().name(), user);
		AuthenticationResponse resp = new AuthenticationResponse();
		resp.setToken(jwtToken);
		return resp;
	}

}
