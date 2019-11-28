package model;

import exceptions.ExistentException;

public interface AddFavoriteHotelToTree {

	/**
	 * Method to add an hotel to the favorite list
	 * @param newHotel - hotel to add
	 * @param node - Node to do recursion
	 */
	void addNewFavoriteHotel(FavoriteHotel newHotel, FavoriteHotel node);
	
	/**
	 * Method to search an hotel in the list
	 * @param newHotel - hotel to add
	 * @param node - Node to do recursion
	 * @return True if the hotel is in the list
	 */
	boolean searchHotel(FavoriteHotel newHotel, FavoriteHotel node);
	
	/**
	 * Method to add an hotel to the favorite list
	 * @param newHotel - New hotel to add
	 * @throws ExistentException - Throw if the hotel was already in the list
	 */
	void addNewFavoriteHotelFinal(Hotel newHotel) throws ExistentException;
}
