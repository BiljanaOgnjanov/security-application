package dto;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FreeTimeSlotDTO {
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date endDate;
	private Integer price;
	private UUID accommodationId;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public UUID getAccommodationId() {
		return accommodationId;
	}
	public void setAccommodationId(UUID accommodationId) {
		this.accommodationId = accommodationId;
	}
	@Override
	public String toString() {
		return "FreeTimeSlotDTO [startDate=" + startDate + ", endDate=" + endDate + ", price=" + price
				+ ", accommodationId=" + accommodationId + "]";
	}
	
}

