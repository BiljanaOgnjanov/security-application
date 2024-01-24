package model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
public class Accommodation {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String location;
	private Integer minGuests;
	private Integer maxGuests;
	private PriceCalculation priceCalculation;
	@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="appuser_id")
    private AppUser appUser;
	@JsonManagedReference
	@OneToMany(mappedBy="accommodation", fetch = FetchType.EAGER)
	private Set<Amenity> amenities = new HashSet<Amenity>();
	@JsonManagedReference
	@OneToMany(mappedBy="accommodation", fetch = FetchType.EAGER)
	private Set<AccommodationImage> accommodationImages = new HashSet<AccommodationImage>();
	@JsonManagedReference
    @OneToMany(mappedBy="accommodation", fetch = FetchType.EAGER)
    private Set<TimeSlot> timeSlot = new HashSet<TimeSlot>();
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
	public Set<Amenity> getAmenities() {
		return amenities;
	}
	public void setAmenities(Set<Amenity> amenities) {
		this.amenities = amenities;
	}
	public Set<AccommodationImage> getAccommodationImages() {
		return accommodationImages;
	}
	public void setAccommodationImages(Set<AccommodationImage> accommodationImages) {
		this.accommodationImages = accommodationImages;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	public PriceCalculation getPriceCalculation() {
		return priceCalculation;
	}
	public void setPriceCalculation(PriceCalculation priceCalculation) {
		this.priceCalculation = priceCalculation;
	}
	
	
	
	
	
	
}
