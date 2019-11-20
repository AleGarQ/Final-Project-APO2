package model;

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
	
	

}
