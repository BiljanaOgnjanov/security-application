package repo;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.TimeSlot;

@Repository
public interface TimeSlotRepo extends JpaRepository<TimeSlot, UUID>{
	@Query("SELECT t.price FROM TimeSlot t WHERE :beginDate >= t.startDate AND :endDate <= t.endDate AND t.accommodation.id = :accommodationId AND t.status = 0")
	Integer getForPriceCalc(@Param("accommodationId") UUID accommodationId,@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

}