package service;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AccommodationImageDTO;
import dto.AccommodationSeachResultDTO;
import dto.AddAccommodationDTO;
import dto.AmenityDTO;
import dto.FreeTimeSlotDTO;
import dto.SearchDTO;
import model.Accommodation;
import model.AccommodationImage;
import model.Amenity;
import model.PriceCalculation;
import model.TimeSlot;
import model.TimeSlotStatus;
import repo.AccommodationImageRepo;
import repo.AccommodationRepo;
import repo.AmenityRepo;
import repo.AppUserRepo;
import repo.TimeSlotRepo;

@Service
public class AccommodationService {
	@Autowired
	private AccommodationRepo accommodationRepo;
	@Autowired
	private AccommodationImageRepo accommodationImageRepo;
	@Autowired
	private AmenityRepo amenityRepo;
	@Autowired
	private TimeSlotRepo timeSlotRepo;
	@Autowired
	private AppUserRepo appUserRepo;
	
	private Long calculateTotalPrice(SearchDTO request, Accommodation accommodation) {
		Long timeDiff = request.getEndDate().getTime() - request.getBeginDate().getTime();
		Long numberOfDays = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		Long priceModifier = (long) 0;
		Integer price = timeSlotRepo.getForPriceCalc(accommodation.getId(), request.getBeginDate(), request.getEndDate());
		if(accommodation.getPriceCalculation() == PriceCalculation.PER_ACCOMMODATION_PER_NIGHT) {
			priceModifier = numberOfDays;
		}else if(accommodation.getPriceCalculation() == PriceCalculation.PER_GUEST_PER_NIGHT){
			priceModifier = numberOfDays*request.getGuests();
		}
	
		return price*priceModifier;
	}
	
	public Set<AccommodationSeachResultDTO> searchAccommodtaion(SearchDTO request) {
		Set<Accommodation> accommodations =  accommodationRepo.search(request.getLocation().toLowerCase(),request.getGuests(),request.getBeginDate(), request.getEndDate());
		Set<Accommodation> accommodations2 = new HashSet<Accommodation>();
		Set<AccommodationSeachResultDTO> accommodationsToReturnDTO = new HashSet<AccommodationSeachResultDTO>();
		for (Accommodation accommodation : accommodations) {
			if(matchesAmenities(accommodation, request.getAmenities())) {
				accommodations2.add(accommodation);
			}
		}
		for (Accommodation accommodation : accommodations2) {
			Long price = calculateTotalPrice(request, accommodation);
			if(price <= request.getMaxPrice() && price >= request.getMinPrice()) {
				AccommodationSeachResultDTO accommoResp = new AccommodationSeachResultDTO();
				accommoResp.setAccommodation(accommodation);
				accommoResp.setCalculatedPrice(price);
				accommodationsToReturnDTO.add(accommoResp);
			}
		}
		return accommodationsToReturnDTO;
	}
	private boolean matchesAmenities(Accommodation accommodation, Set<String> amenities) {
		Set<String> extractedAmenities = new HashSet<String>();
		for (Amenity amenity : accommodation.getAmenities()) {
			extractedAmenities.add(amenity.getAmenity());
		}
		for (String amenity2 : amenities) {
			if(!extractedAmenities.contains(amenity2)) {
				return false;
			}
		}
		return true;
	}
	
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

	public Set<Accommodation> listForHost(String extractUsername) {
		return appUserRepo.findByUsername(extractUsername).getAccommodations();
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
	public Object addTimeSlot(FreeTimeSlotDTO request, String extractUsername) {
		if(checkAccommodationOwnership(request.getAccommodationId(), extractUsername)) {
			Accommodation accommodation = accommodationRepo.findById(request.getAccommodationId()).get();
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setAccommodation(accommodation);
			timeSlot.setEndDate(request.getEndDate());
			timeSlot.setStartDate(request.getStartDate());
			timeSlot.setPrice(request.getPrice());
			timeSlot.setStatus(TimeSlotStatus.FREE);
			timeSlotRepo.save(timeSlot);
		}
		return null;
	}
	private boolean checkAccommodationOwnership(UUID accommodationId, String username) {
		Accommodation accommodation = accommodationRepo.findById(accommodationId).get();
		Set<Accommodation> accommodations = appUserRepo.findByUsername(username).getAccommodations();
		
		for (Accommodation accommodation2 : accommodations) {
			if (accommodation2.getId().equals(accommodation.getId())){
				return true;
			}
		}
		return false;
	}

}
