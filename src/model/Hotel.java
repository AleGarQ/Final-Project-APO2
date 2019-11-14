package model;

public class Hotel {

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------
	
	/**
	 * Attribute that has the Hotel's name
	 */
	private String name;
	/**
	 * Attribute that has the Hotel's id
	 */
	private String id;
	/**
	 * Attribute that has the Hotel's price range
	 */
	private String priceRange;
	/**
	 * Attribute that has the Hotel's stars
	 */
	private int stars;
	/**
	 * Attribute that has the Hotel's score
	 */
	private double score;
	/**
	 * Attribute that has the city where the hotel is
	 */
	private String city;
	/**
	 * Attribute that has relation with the rooms
	 */
	private Room rooms;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Constructor of the Hotel class
	 * @param name - Hotel's name
	 * @param id - Hotel's ID
	 * @param priceRange - Hotel's priceRange
	 * @param stars - Hotel's stars
	 * @param score - Hotel's score
	 */
	public Hotel(String name, String id, String priceRange, int stars, double score, String city) {
		this.name = name;
		this.id = id;
		this.priceRange = priceRange;
		this.stars = stars;
		this.score = score;
		this.city = city;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	
	/**
	 * Method to get the attribute name
	 * @return Hotel's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the attribute name
	 * @param name - New hotel's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the attribute id
	 * @return Hotel's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method to set the attribute id
	 * @param id - New hotel's id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Method to get the attribute priceRange
	 * @return Hotel's price range
	 */
	public String getPriceRange() {
		return priceRange;
	}

	/**
	 * Method to set the attribute priceRange
	 * @param priceRange - New hotel's price range
	 */
	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	/**
	 * Method to get the attribute stars
	 * @return Hotel's stars
	 */
	public int getStars() {
		return stars;
	}

	/**
	 * Method to set the attribute stars
	 * @param stars - New hotel's stars
	 */
	public void setStars(int stars) {
		this.stars = stars;
	}

	/**
	 * Method to get the attribute score
	 * @return Hotel's score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Method to set the attribute score
	 * @param score - New hotel's score
	 */
	public void setScore(double score) {
		this.score = score;
	}
	
	/**
	 * Method to get the attribute city
	 * @return Hotel's city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Method that set the attribute city
	 * @return city - New hotel's city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Method to get the relation rooms
	 * @return The relation with the rooms
	 */
	public Room getRooms() {
		return rooms;
	}

	/**
	 * Method to set the relation rooms
	 * @param rooms - New rooms
	 */
	public void setRooms(Room rooms) {
		this.rooms = rooms;
	}
	
	
}
