package model;

import java.util.ArrayList;

public class SearchHistory {

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------
	
	/**
	 * Attribute text
	 */
	private String text;
	/**
	 * Relation with the SearchHistory in the left
	 */
	private SearchHistory left;
	
	/**
	 * Relation with the SearchHistory in the right
	 */
	private SearchHistory right;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Constructor of the SearchHistory class
	 * @param text - text searched
	 * @param left - SearchHistory in the left
	 * @param right - SearchHistory in the right
	 */
	public SearchHistory(String text, SearchHistory left, SearchHistory right) {
		this.text = text;
		this.left = left;
		this.right = right;
	}

	/**
	 * Method to get the attribute text
	 * @return text searched
	 */
	public String getText() {
		return text;
	}

	/**
	 * Method to set the attribute text
	 * @param text - new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Method to get the relation left
	 * @return left
	 */
	public SearchHistory getLeft() {
		return left;
	}

	/**
	 * Method to set the relation left
	 * @param left - new left
	 */
	public void setLeft(SearchHistory left) {
		this.left = left;
	}

	/**
	 * Method to get the relation right
	 * @return right
	 */
	public SearchHistory getRight() {
		return right;
	}

	/**
	 * Method to set the relation right
	 * @param right - new right
	 */
	public void setRight(SearchHistory right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "SearchHistory [text=" + text + "]";
	}

	/**
	 * Method add objectos to an ArrayList
	 * @param lista - ArrayList where objects will be added
	 */
	public void arrayToArchive(ArrayList<SearchHistory> lista) {

		if (left != null) {
			SearchHistory auxL = left;
			auxL.setLeft(null);
			auxL.setRight(null);
			lista.add(auxL);
			left.arrayToArchive(lista);
		}
		
		lista.add(this);
		
		if (right != null) {
			SearchHistory auxR = right;
			auxR.setLeft(null);
			auxR.setRight(null);
			lista.add(auxR);
			right.arrayToArchive(lista);
		}
	}

	/**
	 * Method to add a record
	 * @param search - Search
	 */
	public void addRecord(SearchHistory search) {
		if (search.getText().equals(text)) {
		} else {
			if (search.getText().compareTo(text) < 0) {
				if (left != null) {
					left.addRecord(search);
				} else {
					left = search;
				}
			} else {
				if (right != null) {
					right.addRecord(search);
				} else {
					right = search;
				}
			}
		}
	}
}
