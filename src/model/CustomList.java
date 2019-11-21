package model;

public class CustomList {

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------

	/**
	 * Attribute that has the list's name
	 */
	private String listName;

	/**
	 * Relation that has hotels of the list
	 */
	private HotelsListed hotelList;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor of the CustomList class
	 * 
	 * @param listname  - List's name
	 * @param hotelList - Hotels in the list
	 */
	public CustomList(String listName, HotelsListed hotelList) {
		this.listName = listName;
		this.hotelList = hotelList;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Method to get the attribute listName
	 * 
	 * @return List's name
	 */
	public String getListName() {
		return listName;
	}

	/**
	 * Method to set the attribute listName
	 * 
	 * @param listName - New name of the custom list
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}

	/**
	 * Method to get the relation hotelList
	 * 
	 * @return List of hotels
	 */
	public HotelsListed getHotelList() {
		return hotelList;
	}

	/**
	 * Method to set the relation hotelList
	 * 
	 * @param hotelList - New list of hotels
	 */
	public void setHotelList(HotelsListed hotelList) {
		this.hotelList = hotelList;
	}

	/**
	 * Method to search an hotel in the list
	 * 
	 * @param newHotelToList - New hotel to add
	 * 
	 * @return If the hotel is in the list or not
	 */
	public boolean searchHotelListed(String id) {
		boolean esta = false;
		if(hotelList != null) {
			HotelsListed aux = hotelList;
			while(aux != null && !esta) {
				if(aux.getId().compareTo(id) == 0) {
					esta = true;
				}else {
					aux = aux.getNext();
				}
			}
		}
		
		return esta;
	}
	
	/**
	 * Method to add an hotel to the list
	 * 
	 * @param newHotelToList - New hotel to add
	 */
	public void addHotelToList(Hotel newHotelToList) {
		if (newHotelToList != null) {
			HotelsListed newHotelToList1 = new HotelsListed(newHotelToList.getName(), newHotelToList.getId(),
					newHotelToList.getPriceRange(), newHotelToList.getStars(), newHotelToList.getScore(), newHotelToList.getCity());
			if(searchHotelListed(newHotelToList1.getId()) == false) {
				if (hotelList == null) {
					hotelList = newHotelToList1;
				} else {
					HotelsListed aux = hotelList;
					aux.setPrevious(newHotelToList1);
					newHotelToList1.setNext(aux);
					hotelList = newHotelToList1;
				}
			}	
		}
	}
}// final
