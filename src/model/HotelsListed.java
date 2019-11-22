package model;

import java.util.Comparator;

public class HotelsListed extends Hotel implements Comparator<HotelsListed>{

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------

	/**
	 * Relation with the previous hotel
	 */
	private HotelsListed previous;

	/**
	 * Relation with the next hotel
	 */
	private HotelsListed next;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor of the Hotel class
	 * 
	 * @param name       - Hotel's name
	 * @param id         - Hotel's ID
	 * @param priceRange - Hotel's priceRange
	 * @param stars      - Hotel's stars
	 * @param score      - Hotel's score
	 * @param city       - City where the hotel is
	 */
	public HotelsListed(String name, String id, String priceRange, int stars, double score, String city) {
		super(name, id, priceRange, stars, score, city);
	}

	/**
	 * Method to get the relation previous
	 * 
	 * @return The relation with the previous hotel in the list
	 */
	public HotelsListed getPrevious() {
		return previous;
	}

	/**
	 * Method to set the relation previous
	 * 
	 * @param previous - New previous hotel
	 */
	public void setPrevious(HotelsListed previous) {
		this.previous = previous;
	}

	/**
	 * Method to get the relation next
	 * 
	 * @return The relation with the next hotel in the list
	 */
	public HotelsListed getNext() {
		return next;
	}

	/**
	 * Method to set the relation next
	 * 
	 * @param next - New next hotel
	 */
	public void setNext(HotelsListed next) {
		this.next = next;
	}

	@Override
	public int compare(HotelsListed o1, HotelsListed o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}// final
