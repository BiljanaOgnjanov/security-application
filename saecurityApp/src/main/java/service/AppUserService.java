package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import config.JwtService;
import dto.AuthenticationResponse;
import dto.ChangeAccountInfoRequest;
import model.AppUser;
import repo.AppUserRepo;

@Service
public class AppUserService {
	@Autowired
	private AppUserRepo appUserRepo;
	@Autowired
	private JwtService jwtService;

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
}