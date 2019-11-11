package model;

public class CustomList {

	private HotelsListed hotelList;
	
	public CustomList(HotelsListed hotelList) {
		this.hotelList = hotelList;
	}
	public HotelsListed getHotelList() {
		return hotelList;
	}
	public void setHotelList(HotelsListed hotelList) {
		this.hotelList = hotelList;
	}
}
