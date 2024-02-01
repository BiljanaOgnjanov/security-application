package service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import config.JwtService;
import dto.AuthenticationRequest;
import dto.AuthenticationResponse;
import dto.RegisterRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import model.AppUser;
import model.Role;
import model.UserToken;
import model.UserTokenUse;
import repo.AppUserRepo;
import repo.UserTokenRepo;

@Service
public class AuthenticationService {
	@Autowired
    private JavaMailSender mailSender;
	@Autowired
	private AppUserRepo appUserRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private UserTokenRepo tokenRepo;
	
    @Async
    public void sendEmail(String to, String subject, String token) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String text ="https://localhost:8080/api/auth/activate/" + token;
        helper.setText(text, true);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setFrom("hotelapplication491@gmail.com");
        mailSender.send(mimeMessage);
    }

	
	
	public AuthenticationResponse registerGuest(RegisterRequest request) {
		AppUser user = new AppUser();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setAdress(request.getAdress());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.GUEST);
		user.setUsername(request.getUsername());
		user.setActivated(false);
		appUserRepo.save(user);
		AuthenticationResponse resp = new AuthenticationResponse();
		AppUser newUser = appUserRepo.findByUsername(user.getUsername());
		UserToken registerToken = new UserToken();
		registerToken.setAppUser(newUser);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 24);
		registerToken.setCreated(cal.getTime());
		registerToken.setStatus(UserTokenUse.ACTIVATION);
		UUID token = UUID.randomUUID();
		registerToken.setId(token);
		newUser.getTokens().add(registerToken);
		tokenRepo.save(registerToken);
		resp.setToken(registerToken.getId().toString());
		try {
			sendEmail(newUser.getEmail(),"Activate Account",registerToken.getId().toString());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		user.setActivated(false);
		appUserRepo.save(user);
		AuthenticationResponse resp = new AuthenticationResponse();
		AppUser newUser = appUserRepo.findByUsername(user.getUsername());
		UserToken registerToken = new UserToken();
		registerToken.setAppUser(newUser);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 24);
		registerToken.setCreated(cal.getTime());
		registerToken.setStatus(UserTokenUse.ACTIVATION);
		UUID token = UUID.randomUUID();
		registerToken.setId(token);
		newUser.getTokens().add(registerToken);
		tokenRepo.save(registerToken);
		resp.setToken(registerToken.getId().toString());
		try {
			sendEmail(newUser.getEmail(),"Activate Account",registerToken.getId().toString());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		AppUser user = appUserRepo.findByUsername(request.getUsername());
		if(user.isActivated()) {
			String jwtToken = jwtService.generateToken(user.getRole().name(), user);
			AuthenticationResponse resp = new AuthenticationResponse();
			resp.setToken(jwtToken);
			return resp;
		}
		return null;
	}

	public Object activate(UUID id) {
		System.out.println(id.toString());
		UserToken token = tokenRepo.findById(id).get();
		if(token.getStatus() == UserTokenUse.USED || token.getCreated().before(new Date())) {
			return null;
		}
		token.setStatus(UserTokenUse.USED);
		tokenRepo.save(token);
		AppUser user = appUserRepo.findById(token.getAppUser().getId()).get();
		user.setActivated(true);
		appUserRepo.save(user);
		return null;
	}

}
