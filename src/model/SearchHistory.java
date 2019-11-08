package model;

public class SearchHistory {

	private SearchHistory left;
	private SearchHistory right;
	
	public SearchHistory(SearchHistory left, SearchHistory right) {
		this.left = left;
		this.right = right;
	}
	public SearchHistory getLeft() {
		return left;
	}
	public void setLeft(SearchHistory left) {
		this.left = left;
	}
	public SearchHistory getRight() {
		return right;
	}
	public void setRight(SearchHistory right) {
		this.right = right;
	}
	
}
