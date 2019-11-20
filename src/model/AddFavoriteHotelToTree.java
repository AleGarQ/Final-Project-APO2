package model;

public interface AddFavoriteHotelToTree {

	void addNewFavoriteHotel(FavoriteHotel newHotel, FavoriteHotel node);
	
	boolean searchHotel(FavoriteHotel newHotel, FavoriteHotel node);
	
	void addNewFavoriteHotelFinal(Hotel newHotel);
}
