package model;

public class CustomList {

	private String listName;
	
	private HotelsListed hotelList;
	
	public CustomList(String listName, HotelsListed hotelList) {
		this.listName = listName;
		this.hotelList = hotelList;
	}
	
	public String getListName() {
		return listName;
	}
	
	public void setListName(String listName) {
		this.listName = listName;
	}

	public HotelsListed getHotelList() {
		return hotelList;
	}
	public void setHotelList(HotelsListed hotelList) {
		this.hotelList = hotelList;
	}
	
	public void addHotelToList(HotelsListed newHotelToList) {
		if(newHotelToList != null) {
			if(hotelList == null) {
				hotelList = newHotelToList;
			}else {
				HotelsListed aux = hotelList;
				aux.setPrevious(newHotelToList);
				newHotelToList.setNext(aux);
				hotelList = newHotelToList;
			}
		}
	}
}
