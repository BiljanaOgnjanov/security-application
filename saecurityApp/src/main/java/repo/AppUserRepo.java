package repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, UUID>{
	AppUser findByEmail(String email);
	AppUser findByUsername(String username);
}

