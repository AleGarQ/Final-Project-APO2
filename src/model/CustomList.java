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
	 * Method to add an hotel to the list
	 * 
	 * @param newHotelToList - New hotel to add
	 */
	public void addHotelToList(HotelsListed newHotelToList) {
		if (newHotelToList != null) {
			if (hotelList == null) {
				hotelList = newHotelToList;
			} else {
				HotelsListed aux = hotelList;
				aux.setPrevious(newHotelToList);
				newHotelToList.setNext(aux);
				hotelList = newHotelToList;
			}
		}
	}
}// final
