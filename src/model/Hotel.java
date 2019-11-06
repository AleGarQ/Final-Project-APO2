package model;

public class Hotel {

	private String name;
	private String id;
	private String priceRange;
	private double stars;
	private double score;
	
	private Hotel left;
	private Hotel right;
	private Room rooms;
	
	public Hotel(String name, String id, String priceRange, double stars, double score) {
		super();
		this.name = name;
		this.id = id;
		this.priceRange = priceRange;
		this.stars = stars;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public double getStars() {
		return stars;
	}

	public void setStars(double stars) {
		this.stars = stars;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
}
