package model;

public class ReservedRoom extends Room {

	private ReservedRoom previous;
	private ReservedRoom next;
	
	public ReservedRoom(String number, String id, int typeOfBeds, boolean availability, ReservedRoom previous, ReservedRoom next) {
		super(number, id, typeOfBeds, availability);
		this.previous = previous;
		this.next = next;
	}
	
	public ReservedRoom getPrevious() {
		return previous;
	}
	public void setPrevious(ReservedRoom previous) {
		this.previous = previous;
	}
	public ReservedRoom getNext() {
		return next;
	}
	public void setNext(ReservedRoom next) {
		this.next = next;
	}
	
}
