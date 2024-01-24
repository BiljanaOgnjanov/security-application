package repo;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import model.Accommodation;

@Repository
public interface AccommodationRepo  extends JpaRepository<Accommodation, UUID>{
}
