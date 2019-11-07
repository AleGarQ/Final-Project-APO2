package model;

public class Room {

	// -----------------------------------------------------------------
	// Constants
	// -----------------------------------------------------------------
	
	/**
	 * It's a constant used to indicate if the room has a personal bed
	 */
	public final static int INDIVIDUAL = 1;
	/**
	 * It's a constant used to indicate if the room has a double bed
	 */
	public final static int DOUBLE = 2;
	/**
	 * It's a constant used to indicate if the room has a double bed and 2 personal beds
	 */
	public final static int FAMILY = 3;
	/**
	 * It's a constant used to indicate if the room has more than 3 beds
	 */
	public final static int MULTIPLE = 4;
	
	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------
	
	/**
	 * Attribute that has the room number
	 */
	private String number;
	
	/**
	 * Attribute that has the room ID
	 */
	private String id;
	
	/**
	 * Attribute that has the type of beds in the room
	 */
	private int typeOfBeds;
	
	/**
	 * Relation with the previous room in the list
	 */
	private Room previous;
	
	/**
	 * Relation with the next room in the list
	 */
	private Room next;
	
	/**
	 * Constructor of the Room class
	 * @param number - Room number
	 * @param id - Room ID
	 * @param typeOfBeds - Type of beds in the room
	 */
	public Room(String number, String id, int typeOfBeds) {
		super();
		this.number = number;
		this.id = id;
		this.typeOfBeds = typeOfBeds;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	
	/**
	 * Method to get the attribute number
	 * @return Room number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Method to set the attribute number
	 * @param New room number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Method to get the attribute id
	 * @return Room ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method to set the attribute id
	 * @param new room id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Method to get the attribute typeOfBeds
	 * @return Type of beds in the room
	 */
	public int getTypeOfBeds() {
		return typeOfBeds;
	}

	/**
	 * Method to set the attribute typeOfBeds
	 * @param New type of beds in the room
	 */
	public void setTypeOfBeds(int typeOfBeds) {
		this.typeOfBeds = typeOfBeds;
	}
}
