package model;

public class FavoriteRoom {

	private FavoriteRoom previous;
	private FavoriteRoom next;
	
	public FavoriteRoom getPrevious() {
		return previous;
	}
	public void setPrevious(FavoriteRoom previous) {
		this.previous = previous;
	}
	public FavoriteRoom getNext() {
		return next;
	}
	public void setNext(FavoriteRoom next) {
		this.next = next;
	}
	
}
