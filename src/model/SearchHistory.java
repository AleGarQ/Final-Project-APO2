package model;

import java.util.ArrayList;

public class SearchHistory {

	private String text;
	private SearchHistory left;
	private SearchHistory right;
	
	public SearchHistory(String text, SearchHistory left, SearchHistory right) {
		this.text = text;
		this.left = left;
		this.right = right;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	@Override
	public String toString() {
		return "SearchHistory [text=" + text + "]";
	}
	
	public void arrayToArchive(ArrayList<SearchHistory> lista){
		
		if(left != null) {
			lista.add(left);
			left.arrayToArchive(lista);
		}
		if(right != null) {
			lista.add(right);
			right.arrayToArchive(lista);
		}	
	}
}
