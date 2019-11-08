package model;

public class ReservedRoom {

	private ReservedRoom previous;
	private ReservedRoom next;
	
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
