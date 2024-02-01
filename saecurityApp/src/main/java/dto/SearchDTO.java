package dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchDTO {
	private String location;
	private Integer guests;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date beginDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private Integer minPrice;
	private Integer maxPrice;
	private Set<String> amenities = new HashSet<String>();
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getGuests() {
		return guests;
	}
	public void setGuests(Integer guests) {
		this.guests = guests;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Set<String> getAmenities() {
		return amenities;
	}
	public void setAmenities(Set<String> amenities) {
		this.amenities = amenities;
	}
	@Override
	public String toString() {
		return "SearchDTO [location=" + location + ", guests=" + guests + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", amenities=" + amenities + "]";
	}
	
	
}
