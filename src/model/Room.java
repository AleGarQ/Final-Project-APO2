package model;

import java.io.Serializable;

public class Room implements Serializable{

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
	 * Attribute that has the availability of the room
	 */
	private boolean availability;
	
	/**
	 * Attribute that has the hotel where is the room 
	 */
	private String hotel;
	
	/**
	 * Relation with the previous room in the list
	 */
	private Room previous;
	
	/**
	 * Relation with the next room in the list
	 */
	private Room next;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Constructor of the Room class
	 * @param number - Room number
	 * @param id - Room ID
	 * @param typeOfBeds - Type of beds in the room
	 */
	public Room(String number, String id, int typeOfBeds, boolean availability, String hotel) {
		super();
		this.number = number;
		this.id = id;
		this.typeOfBeds = typeOfBeds;
		this.availability = availability;
		this.hotel = hotel;
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
	 * @param number - New room number
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
	 * @param id - new room id
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
	 * @param typeOfBeds - New type of beds in the room
	 */
	public void setTypeOfBeds(int typeOfBeds) {
		this.typeOfBeds = typeOfBeds;
	}
	
	/**
	 * Method to get the attribute availability
	 * @return Availability of the room
	 */
	public boolean getAvailability() {
		return availability;
	}
	
	/**
	 * Method to set the attribute availability
	 * @param availability - New availability of the room
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	/**
	 * Method to get the relation previous
	 * @return The relation with the previous room in the list
	 */
	public Room getPrevious() {
		return previous;
	}

	/**
	 * Method to set the relation previous
	 * @param previous - New previous room
	 */
	public void setPrevious(Room previous) {
		this.previous = previous;
	}

	/**
	 * Method to get the relation next
	 * @return The relation with the next room in the list
	 */
	public Room getNext() {
		return next;
	}

	/**
	 * Method to set the relation next
	 * @param next - New next room
	 */
	public void setNext(Room next) {
		this.next = next;
	}

	/**
	 * Method to get the attribute hotel
	 * @return Hotel where is the room
	 */
	public String getHotel() {
		return hotel;
	}
	
	/**
	 * Method to set the attribute hotel
	 * @param hotel - New hotel where is the room
	 */
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
}//final
