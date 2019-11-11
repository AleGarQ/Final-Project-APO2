package model;

public class FavoriteRoom {

	private FavoriteRoom left;
	private FavoriteRoom right;
	
	public FavoriteRoom(FavoriteRoom left, FavoriteRoom right) {
		this.left = left;
		this.right = right;
	}
	
	public FavoriteRoom getLeft() {
		return left;
	}
	public void setLeft(FavoriteRoom left) {
		this.left = left;
	}
	public FavoriteRoom getRight() {
		return right;
	}
	public void setRight(FavoriteRoom right) {
		this.right = right;
	}
	
}
