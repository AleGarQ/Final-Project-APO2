package model;

import java.util.ArrayList;

public class FavoriteHotel extends Hotel{

	private FavoriteHotel left, right;

	public FavoriteHotel(String name, String id, String priceRange, int stars, double score, String city, 
						FavoriteHotel left, FavoriteHotel right) {
		super(name, id, priceRange, stars, score, city);
		this.left = left;
		this.right = right;
	}

	public FavoriteHotel getLeft() {
		return left;
	}

	public void setLeft(FavoriteHotel left) {
		this.left = left;
	}

	public FavoriteHotel getRight() {
		return right;
	}

	public void setRight(FavoriteHotel right) {
		this.right = right;
	}

	public String toString() {
		return "FavoriteHotel [name=" + getName() + ", id=" + getId() + ", priceRange=" + getPriceRange() + ", stars=" + getStars() + ", score=" + 
								 getScore() + ", city=" + getCity() + "]";
	}
	
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
