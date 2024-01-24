package dto;

import java.util.HashSet;
import java.util.Set;

import model.PriceCalculation;


public class AddAccommodationDTO {
	private String name;
	private String location;
	private Integer minGuests;
	private Integer maxGuests;
	private PriceCalculation priceCalculation;
	private Set<AmenityDTO> amenities = new HashSet<AmenityDTO>();
	private Set<AccommodationImageDTO> accommodationImages = new HashSet<AccommodationImageDTO>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getMinGuests() {
		return minGuests;
	}
	public void setMinGuests(Integer minGuests) {
		this.minGuests = minGuests;
	}
	public Integer getMaxGuests() {
		return maxGuests;
	}
	public void setMaxGuests(Integer maxGuests) {
		this.maxGuests = maxGuests;
	}
	public Set<AmenityDTO> getAmenities() {
		return amenities;
	}
	public void setAmenities(Set<AmenityDTO> amenities) {
		this.amenities = amenities;
	}
	public Set<AccommodationImageDTO> getAccommodationImages() {
		return accommodationImages;
	}
	public void setAccommodationImages(Set<AccommodationImageDTO> accommodationImages) {
		this.accommodationImages = accommodationImages;
	}
	
	
	public PriceCalculation getPriceCalculation() {
		return priceCalculation;
	}
	public void setPriceCalculation(PriceCalculation priceCalculation) {
		this.priceCalculation = priceCalculation;
	}
	@Override
	public String toString() {
		return "AddAccommodationDTO [name=" + name + ", location=" + location + ", minGuests=" + minGuests
				+ ", maxGuests=" + maxGuests + ", amenities=" + amenities + ", accommodationImages="
				+ accommodationImages + "]";
	}
	
}
