package service;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AccommodationImageDTO;
import dto.AddAccommodationDTO;
import dto.AmenityDTO;
import model.Accommodation;
import model.AccommodationImage;
import model.Amenity;
import repo.AccommodationImageRepo;
import repo.AccommodationRepo;
import repo.AmenityRepo;
import repo.AppUserRepo;

@Service
public class AccommodationService {
	@Autowired
	private AccommodationRepo accommodationRepo;
	@Autowired
	private AccommodationImageRepo accommodationImageRepo;
	@Autowired
	private AmenityRepo amenityRepo;
	@Autowired
	private AppUserRepo appUserRepo;
	
	private Accommodation mapForCreation(AddAccommodationDTO request) {
		Accommodation accommodation = new Accommodation();
		accommodation.setName(request.getName());
		accommodation.setLocation(request.getLocation());
		accommodation.setMinGuests(request.getMinGuests());
		accommodation.setMaxGuests(request.getMaxGuests());
		accommodation.setPriceCalculation(request.getPriceCalculation());
		for (AmenityDTO amenityReq : request.getAmenities()) {
			Amenity amenity = new Amenity();
			amenity.setAmenity(amenityReq.getAmenity());
			amenity.setAccommodation(accommodation);
			accommodation.getAmenities().add(amenity);
		}
		for (AccommodationImageDTO imgReq : request.getAccommodationImages()) {
			AccommodationImage img = new AccommodationImage();
			img.setImg(imgReq.getImg());
			img.setAccommodation(accommodation);
			accommodation.getAccommodationImages().add(img);
		}
		return accommodation;
		
	}

	
	public Object addAccommodation(AddAccommodationDTO request, String extractUsername) {
		Accommodation accommodation = mapForCreation(request);
		accommodation.setAppUser(appUserRepo.findByUsername(extractUsername));
		accommodationRepo.save(accommodation);
		for (Amenity amenity : accommodation.getAmenities()) {
			amenityRepo.save(amenity);
		}
		for (AccommodationImage img : accommodation.getAccommodationImages()) {
			accommodationImageRepo.save(img);
		}
		return accommodation;
	}
	
	public Accommodation getById(UUID id) {
		return accommodationRepo.findById(id).get();
	}

}
