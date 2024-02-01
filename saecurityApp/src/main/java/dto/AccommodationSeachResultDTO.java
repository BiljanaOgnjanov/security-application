package dto;


import model.Accommodation;

public class AccommodationSeachResultDTO {
	private Accommodation accommodation = new Accommodation();
	private Long calculatedPrice;
	public Accommodation getAccommodation() {
		return accommodation;
	}
	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}
	public Long getCalculatedPrice() {
		return calculatedPrice;
	}
	public void setCalculatedPrice(Long calculatedPrice) {
		this.calculatedPrice = calculatedPrice;
	}
	
	
}
