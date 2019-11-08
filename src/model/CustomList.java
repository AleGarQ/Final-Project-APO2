package model;

public class CustomList {

	private CustomList previous;
	private CustomList next;
	
	public CustomList getPrevious() {
		return previous;
	}
	public void setPrevious(CustomList previous) {
		this.previous = previous;
	}
	public CustomList getNext() {
		return next;
	}
	public void setNext(CustomList next) {
		this.next = next;
	}
	
}
