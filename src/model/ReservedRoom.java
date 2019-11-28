package model;

public class ReservedRoom extends Room {

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------
	
	/**
	 * Relation with the previous ReservedRoom
	 */
	private ReservedRoom previous;
	/**
	 * Relation with the next ReservedRoom
	 */
	private ReservedRoom next;
	
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor of the ReservedRoom class
	 * 
	 * @param number  - ReservedRoom's number
	 * @param id - ReservedRoom's id
	 * @param typeOfBeds - ReservedRoom's type of beds
	 * @param availability - ReservedRoom's availability
	 * @param hotel - ReservedRoom's hotel where this is
	 * @param previous - Previous ReservedRoom
	 * @param next - Next ReservedRoom
	 */
	public ReservedRoom(String number, String id, int typeOfBeds, boolean availability, String hotel, ReservedRoom previous, ReservedRoom next) {
		super(number, id, typeOfBeds, availability, hotel);
		this.previous = previous;
		this.next = next;
	}
	
	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	
	/**
	 * Method to get the relation previous
	 */
	public ReservedRoom getPrevious() {
		return previous;
	}
	
	/**
	 * Method to set the relation previous
	 * @param previous - New previous
	 */
	public void setPrevious(ReservedRoom previous) {
		this.previous = previous;
	}
	
	/**
	 * Method to get the relation next
	 */
	public ReservedRoom getNext() {
		return next;
	}
	
	/**
	 * Method to set the relation next
	 * @param next - New next
	 */
	public void setNext(ReservedRoom next) {
		this.next = next;
	}

	/**
	 * Method to get the reserved room information
	 */
	@Override
	public String toString() {
		return "\nNumero de habitacion: " + getNumber() + "\nId: " + getId() + "\nCantidad de camas: " + getTypeOfBeds() + "\nDisponibilidad: " 
				+ getAvailability() +  "\nHotel: " + getHotel() +  "\n------------------------------";
	}
	
}
