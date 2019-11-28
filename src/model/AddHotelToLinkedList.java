package model;

public interface AddHotelToLinkedList {

	/**
	 * Method to search an hotel in the list
	 * @param id - hotel's id
	 * @return - True if the hotel is in the list
	 */
	boolean searchHotelListed(String id);

	/**
	 * Method to add an hotel to the list
	 * @param newHotelToList - New hotel to add
	 */
	void addHotelToList(Hotel newHotelToList);

}
