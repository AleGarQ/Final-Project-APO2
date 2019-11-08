package model;

public class CustomList {

	private CustomList left;
	private CustomList rigth;
	private HotelsListed hotelList;
	
	public CustomList(CustomList left, CustomList rigth, HotelsListed hotelList) {
		this.left = left;
		this.rigth = rigth;
		this.hotelList = hotelList;
	}
	public CustomList getLeft() {
		return left;
	}
	public void setLeft(CustomList left) {
		this.left = left;
	}
	public CustomList getRigth() {
		return rigth;
	}
	public void setRigth(CustomList rigth) {
		this.rigth = rigth;
	}
	public HotelsListed getHotelList() {
		return hotelList;
	}
	public void setHotelList(HotelsListed hotelList) {
		this.hotelList = hotelList;
	}
}
