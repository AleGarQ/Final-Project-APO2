package model;

public class CustomList implements AddHotelToLinkedList{

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------

	/**
	 * Attribute that has the list's name
	 */
	private String listName;

	/**
	 * Relation that has hotels of the list
	 */
	private HotelsListed hotelList;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor of the CustomList class
	 * 
	 * @param listname  - List's name
	 * @param hotelList - Hotels in the list
	 */
	public CustomList(String listName, HotelsListed hotelList) {
		this.listName = listName;
		this.hotelList = hotelList;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Method to get the attribute listName
	 * 
	 * @return List's name
	 */
	public String getListName() {
		return listName;
	}

	/**
	 * Method to set the attribute listName
	 * 
	 * @param listName - New name of the custom list
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}

	/**
	 * Method to get the relation hotelList
	 * 
	 * @return List of hotels
	 */
	public HotelsListed getHotelList() {
		return hotelList;
	}

	/**
	 * Method to set the relation hotelList
	 * 
	 * @param hotelList - New list of hotels
	 */
	public void setHotelList(HotelsListed hotelList) {
		this.hotelList = hotelList;
	}

	/**
	 * Method to search an hotel in the list
	 * 
	 * @param newHotelToList - New hotel to add
	 * 
	 * @return If the hotel is in the list or not
	 */
	
	@Override
	public boolean searchHotelListed(String id) {
		boolean esta = false;
		if(hotelList != null) {
			HotelsListed aux = hotelList;
			while(aux != null && !esta) {
				if(aux.getId().compareTo(id) == 0) {
					esta = true;
				}else {
					aux = aux.getNext();
				}
			}
		}
		
		return esta;
	}
	
	/**
	 * Method to add an hotel to the list
	 * 
	 * @param newHotelToList - New hotel to add
	 */
	
	@Override
	public void addHotelToList(Hotel newHotelToList) {
		if (newHotelToList != null) {
			HotelsListed newHotelToList1 = new HotelsListed(newHotelToList.getName(), newHotelToList.getId(),
					newHotelToList.getPriceRange(), newHotelToList.getStars(), newHotelToList.getScore(), newHotelToList.getCity());
			if(searchHotelListed(newHotelToList1.getId()) == false) {
				if (hotelList == null) {
					hotelList = newHotelToList1;
				} else {
					HotelsListed aux = hotelList;
					aux.setPrevious(newHotelToList1);
					newHotelToList1.setNext(aux);
					hotelList = newHotelToList1;
				}
			}	
		}
	}
	
	public void sortHotelsByName() {		
		if(hotelList != null) {
			boolean ord;
			do {
				ord = false;
				HotelsListed temp = hotelList;
				HotelsListed tempN = hotelList.getNext();
				HotelsListed temp2 = null;
				
			
				while(tempN != null) {
					if(temp.compare(temp, tempN) > 0) {
						ord = true;
						if(temp2 != null) {
							HotelsListed next2 = tempN.getNext();
							temp2.setNext(tempN);
							tempN.setPrevious(temp2);
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(next2);
						}else {
							HotelsListed tempN2 = tempN.getNext();
							hotelList = tempN;
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(tempN2);
							tempN2.setPrevious(temp);
						}
						temp2= tempN;
						tempN = temp.getNext().getNext();
					}else {
						temp2 = temp;
						temp = tempN;
						tempN = tempN.getNext();
					}
				}
			}while(ord);
		}
	}
	
	public void sortHotelsByPriceRange() {		
		if(hotelList != null) {
			boolean ord;
			do {
				ord = false;
				HotelsListed temp = hotelList;
				HotelsListed tempN = hotelList.getNext();
				HotelsListed temp2 = null;
				
			
				while(tempN != null) {
					if(temp.getPriceRange().compareTo(tempN.getPriceRange()) > 0) {
						ord = true;
						if(temp2 != null) {
							HotelsListed next2 = tempN.getNext();
							temp2.setNext(tempN);
							tempN.setPrevious(temp2);
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(next2);
						}else {
							HotelsListed tempN2 = tempN.getNext();
							hotelList = tempN;
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(tempN2);
							tempN2.setPrevious(temp);
						}
						temp2= tempN;
						tempN = temp.getNext().getNext();
					}else {
						temp2 = temp;
						temp = tempN;
						tempN = tempN.getNext();
					}
				}
			}while(ord);
		}
	}
	
	public void sortHotelsByStarsInDescendingOrder() {		
		if(hotelList != null) {
			boolean ord;
			do {
				ord = false;
				HotelsListed temp = hotelList;
				HotelsListed tempN = hotelList.getNext();
				HotelsListed temp2 = null;
				
			
				while(tempN != null) {
					if(temp.getStars() < tempN.getStars()) {
						ord = true;
						if(temp2 != null) {
							HotelsListed next2 = tempN.getNext();
							temp2.setNext(tempN);
							tempN.setPrevious(temp2);
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(next2);
						}else {
							HotelsListed tempN2 = tempN.getNext();
							hotelList = tempN;
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(tempN2);
							tempN2.setPrevious(temp);
						}
						temp2= tempN;
						tempN = temp.getNext().getNext();
					}else {
						temp2 = temp;
						temp = tempN;
						tempN = tempN.getNext();
					}
				}
			}while(ord);
		}
	}
	
	public void sortHotelsByScoreInDescendingOrder() {		
		if(hotelList != null) {
			boolean ord;
			do {
				ord = false;
				HotelsListed temp = hotelList;
				HotelsListed tempN = hotelList.getNext();
				HotelsListed temp2 = null;
				
			
				while(tempN != null) {
					if(temp.getScore() < tempN.getScore()) {
						ord = true;
						if(temp2 != null) {
							HotelsListed next2 = tempN.getNext();
							temp2.setNext(tempN);
							tempN.setPrevious(temp2);
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(next2);
						}else {
							HotelsListed tempN2 = tempN.getNext();
							hotelList = tempN;
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(tempN2);
							tempN2.setPrevious(temp);
						}
						temp2= tempN;
						tempN = temp.getNext().getNext();
					}else {
						temp2 = temp;
						temp = tempN;
						tempN = tempN.getNext();
					}
				}
			}while(ord);
		}
	}
	
	public void sortHotelsByCity() {		
		if(hotelList != null) {
			boolean ord;
			do {
				ord = false;
				HotelsListed temp = hotelList;
				HotelsListed tempN = hotelList.getNext();
				HotelsListed temp2 = null;
				
			
				while(tempN != null) {
					if(temp.getCity().compareTo(tempN.getCity()) > 0) {
						ord = true;
						if(temp2 != null) {
							HotelsListed next2 = tempN.getNext();
							temp2.setNext(tempN);
							tempN.setPrevious(temp2);
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(next2);
						}else {
							HotelsListed tempN2 = tempN.getNext();
							hotelList = tempN;
							tempN.setNext(temp);
							temp.setPrevious(tempN);
							temp.setNext(tempN2);
							tempN2.setPrevious(temp);
						}
						temp2= tempN;
						tempN = temp.getNext().getNext();
					}else {
						temp2 = temp;
						temp = tempN;
						tempN = tempN.getNext();
					}
				}
			}while(ord);
		}
	}
	
	public String hotelsListedText() {
		String toString = "";
		HotelsListed aux = hotelList;
		
		while(aux != null) {
			toString += aux.toString() + ", ";
		}
		
		return toString;
	}

	@Override
	public String toString() {
		return "CustomList [listName=" + listName + hotelsListedText() + "]";
	}
	
	
}// final
