package model;

public class HotelsListed extends Hotel{
	
	private HotelsListed previous, next;

	public HotelsListed(String name, String id, String priceRange, double stars, double score) {
		super(name, id, priceRange, stars, score);	
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
