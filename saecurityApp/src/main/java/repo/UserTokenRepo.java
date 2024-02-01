package repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import model.UserToken;

@Repository
public interface UserTokenRepo extends JpaRepository<UserToken, UUID>{

}
