package model;

import java.util.ArrayList;

public class FavoriteHotel extends Hotel{

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------
	
	/**
	 * Relations with the FavoriteHotel in the left and right
	 */
	private FavoriteHotel left, right;

	/**
	 * Constructor of the FavoriteHotel class
	 * @param name - hotel's name
	 * @param id - hotel's id
	 * @param priceRange - hotel's price range
	 * @param stars - hotel's stars
	 * @param score - hotel's score
	 * @param city - City where is the hotel
	 * @param left - FavoriteHotel in the left
	 * @param right - FavoriteHotel in the right
	 */
	public FavoriteHotel(String name, String id, String priceRange, int stars, double score, String city, 
						FavoriteHotel left, FavoriteHotel right) {
		super(name, id, priceRange, stars, score, city);
		this.left = left;
		this.right = right;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	
	/**
	 * Method to get the FavoriteHotel in the left
	 * @return left
	 */
	public FavoriteHotel getLeft() {
		return left;
	}

	/**
	 * Method to set the FavoriteHotel in the left
	 * @param left - New left
	 */
	public void setLeft(FavoriteHotel left) {
		this.left = left;
	}

	/**
	 * Method to get the FavoriteHotel in the right
	 * @return right
	 */
	public FavoriteHotel getRight() {
		return right;
	}

	/**
	 * Method to set the FavoriteHotel in the right
	 * @param right - New right
	 */
	public void setRight(FavoriteHotel right) {
		this.right = right;
	}

	/**
	 * Method to get the hotel information
	 */
	public String toString() {
		return "FavoriteHotel [name=" + getName() + ", id=" + getId() + ", priceRange=" + getPriceRange() + ", stars=" + getStars() + ", score=" + 
								 getScore() + ", city=" + getCity() + "]";
	}
	
	/**
	 * Method to add objects in an ArrayList
	 * @param lista - ArrayList where objects will be added
	 */
	public void arrayToArchive(ArrayList<FavoriteHotel> lista){
		
		if(left != null) {
			FavoriteHotel auxL = left;
			auxL.setLeft(null);
			auxL.setRight(null);
			lista.add(auxL);
			left.arrayToArchive(lista);
		}
		if(right != null) {
			FavoriteHotel auxR = right;
			auxR.setLeft(null);
			auxR.setRight(null);
			lista.add(auxR);
			right.arrayToArchive(lista);
		}	
	}
	
}
