package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AppUser;
import repo.AppUserRepo;

@Service
public class AppUserService {
	@Autowired
	private AppUserRepo appUserRepo;

}