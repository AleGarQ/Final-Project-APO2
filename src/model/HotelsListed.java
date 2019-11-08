package model;

public class HotelsListed {

	private HotelsListed previous, next;

	public HotelsListed(HotelsListed previous, HotelsListed next) {
		this.previous = previous;
		this.next = next;
	}

	public HotelsListed getPrevious() {
		return previous;
	}

	public void setPrevious(HotelsListed previous) {
		this.previous = previous;
	}

	public HotelsListed getNext() {
		return next;
	}

	public void setNext(HotelsListed next) {
		this.next = next;
	}
	
	
}
