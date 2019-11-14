package model;

public class HotelsListed extends Hotel{
	
	private HotelsListed previous, next;

	public HotelsListed(String name, String id, String priceRange, int stars, double score, String city) {
		super(name, id, priceRange, stars, score, city);	
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
