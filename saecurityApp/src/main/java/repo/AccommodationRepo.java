package repo;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Accommodation;

@Repository
public interface AccommodationRepo  extends JpaRepository<Accommodation, UUID>{

	@Query("SELECT DISTINCT a FROM Accommodation a INNER JOIN Amenity am ON a.id = am.accommodation.id "
			+ "INNER JOIN TimeSlot t on a.id = t.accommodation.id "
			+ "WHERE LOWER(a.location) LIKE %:location% "
			+ "AND :guests BETWEEN a.minGuests AND a.maxGuests "
			+ "AND :beginDate >= t.startDate AND :endDate <= t.endDate AND t.status = 0")
	Set<Accommodation> search(@Param("location") String location, @Param("guests") Integer guests, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}
