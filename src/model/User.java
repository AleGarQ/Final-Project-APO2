package model;

import java.util.ArrayList;
import java.util.Comparator;

import exceptions.ExistentException;
import exceptions.ListNotFoundException;

public class User extends Person implements Comparator<User>, AddFavoriteHotelToTree, addRecordToTree {

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------

	/**
	 * Attribute that has the user's password
	 */
	private String password;
	/**
	 * Attribute that has the user's email
	 */
	private String email;
	/**
	 * Attribute that has the user's phone number
	 */
	private String phoneNumber;
	/**
	 * Relation with the user in the left
	 */
	private User left;
	/**
	 * Relation with the user in the right
	 */
	private User right;
	/**
	 * Relation with the reserved rooms
	 */
	private ReservedRoom rRooms;
	/**
	 * Relation with favorites rooms
	 */
	private FavoriteHotel fHotel;
	/**
	 * ArrayList of custom lists
	 */
	private ArrayList<CustomList> customList;
	/**
	 * Relation with the search history
	 */
	private SearchHistory record;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor of the User class
	 * 
	 * @param name        - User's name
	 * @param id          - User's id
	 * @param password    - User's password
	 * @param email       - User's email
	 * @param age         - User's age
	 * @param phoneNumber - User's phone number
	 * @param left        - Relation with the user at the left
	 * @param right       - Relation with the user at the right
	 */
	public User(String name, String id, String age, String password, String email, String phoneNumber, User left,
			User right) {
		super(name, id, age);
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.left = left;
		this.right = right;
		customList = new ArrayList<>();
		sortListByName();
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	

	

	

	/**
	 * Method to get the attribute password
	 * 
	 * @return User's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method to set the attribute password
	 * 
	 * @param password - New User's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method to get the attribute email
	 * 
	 * @return User's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method to set the attribute email
	 * 
	 * @param email - New User's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method to get the attribute phoneNumber
	 * 
	 * @return User's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Method to set the attribute phoneNumber
	 * 
	 * @param phoneNumber - New User's phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Method to get the relation left
	 * 
	 * @return The user in the left
	 */
	public User getLeft() {
		return left;
	}

	/**
	 * Method to set the relation left
	 * 
	 * @param left - New User in the left
	 */
	public void setLeft(User left) {
		this.left = left;
	}

	/**
	 * Method to get the relation right
	 * 
	 * @return The user in the right
	 */
	public User getRight() {
		return right;
	}

	/**
	 * Method to set the relation right
	 * 
	 * @param right - New User in the right
	 */
	public void setRight(User right) {
		this.right = right;
	}

	/**
	 * Method to get the relation rRooms
	 * 
	 * @return Reserved rooms
	 */
	public ReservedRoom getRRooms() {
		return rRooms;
	}

	/**
	 * Method to set the relation rRooms
	 * 
	 * @param rRooms - New reserved rooms
	 */
	public void setRRooms(ReservedRoom rRooms) {
		this.rRooms = rRooms;
	}

	/**
	 * Method to get the relation fHotel
	 * 
	 * @return Favorite hotel
	 */
	public FavoriteHotel getfHotel() {
		return fHotel;
	}

	/**
	 * Method to set the relation fRooms
	 * 
	 * @param fRooms - New favorite rooms
	 */
	public void setfHotel(FavoriteHotel fHotel) {
		this.fHotel = fHotel;
	}

	/**
	 * Method to get the ArrayList customList
	 * 
	 * @return Custom lists
	 */
	public ArrayList<CustomList> getCustomList() {
		return customList;
	}

	/**
	 * Method to set the ArrayList customList
	 * 
	 * @param customList - New custom lists
	 */
	public void setCustomList(ArrayList<CustomList> customList) {
		this.customList = customList;
	}

	/**
	 * Method to get the relation record
	 * 
	 * @return Search history
	 */
	public SearchHistory getRecord() {
		return record;
	}

	/**
	 * Method to set the relation record
	 * 
	 * @param record - New search history
	 */
	public void setRecord(SearchHistory record) {
		this.record = record;
	}

	/**
	 * Method to add a reserved room
	 * 
	 * @param chosenRoom - New reserved rooms
	 */
	public void addReservedRoom(ReservedRoom chosenRoom) {
		ReservedRoom aux = rRooms;
		chosenRoom.setAvailability(false);
		if (aux == null) {
			setRRooms(chosenRoom);
		} else {
			chosenRoom.setNext(aux);
			aux.setPrevious(chosenRoom);
			rRooms = chosenRoom;
		}
	}

	/**
	 * Method to set the relation rRooms
	 * 
	 * @param listName - Name of the new custom list
	 */
	public void createNewCustomList(String listName) {
		CustomList c = new CustomList(listName, null);
		customList.add(c);
	}

	/**
	 * Method to compare 2 users
	 * 
	 * @param u1 - First user to compare
	 * @param u2 - Second user to compare
	 */
	@Override
	public int compare(User u1, User u2) {
		int obtainedNumber = u1.getName().compareTo(u2.getName());
		return obtainedNumber;
	}

	/**
	 * Method to sort the custom lists by name
	 */
	public void sortListByName() {
		for (int i = 0; i < customList.size() - 1; i++) {
			int min = i;
			for (int j = i + 1; j < customList.size(); j++) {
				if (customList.get(j).getListName().compareTo(customList.get(min).getListName()) < 0) {
					min = j;
				}
			}
			if (i != min) {
				CustomList aux = customList.get(i);
				customList.set(i, customList.get(min));
				customList.set(min, aux);
			}
		}
	}

	/**
	 * Method to add an hotel to a custom list
	 * 
	 * @param listName       - Name of the list where the user will add the hotel
	 * @param newHotelToList - Hotel to add
	 * @throws ListNotFoundException - Exception if the list is not found
	 */
	public void addHotelToCustomList(String listName, HotelsListed newHotelToList) throws ListNotFoundException {
		boolean found = false;
		int start = 0;
		int end = customList.size() - 1;

		while (start <= end && !found) {
			int half = (start + end) / 2;
			if (customList.get(half).getListName().compareTo(listName) == 0) {
				found = true;
				customList.get(half).addHotelToList(newHotelToList);
			} else if (customList.get(half).getListName().compareTo(listName) > 0) {
				end = half - 1;
			} else {
				start = half + 1;
			}
		}
		if (found == false) {
			throw new ListNotFoundException("No se ha encontrado ninguna lista con ese nombre");
		}
	}

	/**
	 * Method to add a FavoriteRoom
	 * 
	 * @param newHotel - New Hotel to add to favorites
	 * @param node - Node to do recursion
	 */
	@Override
	public void addNewFavoriteHotel(FavoriteHotel newHotel, FavoriteHotel node) {
		if (newHotel.getId().compareTo(node.getId()) < 0) {
			if (node.getLeft() != null) {
				addNewFavoriteHotel(newHotel, node.getLeft());
			} else {
				node.setLeft(newHotel);
			}
		} else {
			if (node.getLeft() != null) {
				addNewFavoriteHotel(newHotel, node.getRight());
			} else {
				node.setRight(newHotel);
			}
		}
	}

	/**
	 * Method to search if the the hotel is in the list 
	 * 
	 * @param newHotel - New Hotel to add to favorites
	 * @param node - Node to do recursion
	 * @return True if the hotel is in the list
	 */
	@Override
	public boolean searchHotel(FavoriteHotel newHotel, FavoriteHotel node) {
		if (newHotel.getId().compareTo(node.getId()) == 0) {
			return true;
		} else if (newHotel.getId().compareTo(node.getId()) < 0) {
			if (node.getLeft() != null) {
				return searchHotel(newHotel, node.getLeft());
			}
		}else {
			if(node.getRight() != null) {
				return searchHotel(newHotel, node.getRight());
			}
		}
		return false;
	}

	/**
	 * Method to add an hotel to the favorite's list 
	 * 
	 * @param newHotel - New Hotel to add to favorites
	 */
	@Override
	public void addNewFavoriteHotelFinal(Hotel newHotel) throws ExistentException{
		
		if(newHotel != null) {
		
			FavoriteHotel newHotel1 = new FavoriteHotel(newHotel.getName(), newHotel.getId(), newHotel.getPriceRange(),
														newHotel.getStars(), newHotel.getScore(), newHotel.getCity(), null, null);
			
			if(fHotel != null) {
				if(searchHotel(newHotel1, fHotel) == false) {
					addNewFavoriteHotel(newHotel1, fHotel);
				}else {
					throw new ExistentException("El hotel ya está en la lista de favoritos");
				}
			}else {
				fHotel = newHotel1;
			}
		}
	}
	
	/**
	 * Method to search a custom list by name
	 * 
	 * @param listName - List name
	 * @return The custom list if this is found
	 */
	public CustomList searchCustomListByName(String listName) throws ListNotFoundException {
		CustomList found1 = null;
		
		boolean found = false;
		int start = 0;
		int end = customList.size() - 1;

		while (start <= end && !found) {
			int half = (start + end) / 2;
			if (customList.get(half).getListName().compareTo(listName) == 0) {
				found = true;
				found1 = customList.get(half);
			} else if (customList.get(half).getListName().compareTo(listName) > 0) {
				end = half - 1;
			} else {
				start = half + 1;
			}
		}
		if (found == false) {
			throw new ListNotFoundException("No se ha encontrado ninguna lista con ese nombre");
		}
		
		return found1;
	}
	
	/**
	 * Method to get the toString of all the favorite hotels
	 * 
	 * @return An String with the toString of all the favorite hotels
	 */
	public String favoriteHotelsText() {
		
		String toString = "";
		ArrayList<FavoriteHotel> epale = new ArrayList<FavoriteHotel>();
		
		if(fHotel != null) {
			FavoriteHotel auxSH = fHotel;
			auxSH.setLeft(null);
			auxSH.setRight(null);
			epale.add(auxSH);
			fHotel.arrayToArchive(epale);
		}
		
		for(int i = 0; i < epale.size(); i++) {
			toString += epale.get(i).toString() + ", ";
		}
		
		return toString;
	}
	
	/**
	 * Method to get the toString of all the reserved rooms
	 * 
	 * @return An String with the toString of all the reserved rooms
	 */
	public String reservedRoomText() {
		String toString = "";
		ReservedRoom aux = rRooms;
		
		while(aux != null) {
			toString += aux.toString() + ", ";
		}
		
		return toString;
	}
	
	/**
	 * Method to get the toString of all the custom lists
	 * 
	 * @return An String with the toString of all the custom lists
	 */
	public String customListText() {
		String toString = "";
		
		for(int i = 0; i < customList.size(); i++) {
			toString += customList.get(i).hotelsListedText() + ", ";
		}
		
		return toString;
	}
	
	/**
	 * Method to get the toString of all the search history
	 * 
	 * @return An String with the toString of all the search history
	 */
	public String searchHistoryText() {
		
		String toString = "";
		ArrayList<SearchHistory> epale = new ArrayList<SearchHistory>();
		
		if(record != null) {
			SearchHistory auxSH = record;
			auxSH.setLeft(null);
			auxSH.setRight(null);
			epale.add(auxSH);
			record.arrayToArchive(epale);
		}
		
		for(int i = 0; i < epale.size(); i++) {
			toString += epale.get(i).toString() + ", ";
		}
		
		return toString;
	}
	
	/**
	 * Method to get the user information
	 * 
	 * @return All the user information
	 */
	@Override
	public String toString() {
		return "+User [name=" + getName() + ", id=" + getId() + ", password=" + password + ", email=" + email + ", age=" + getAge()
				+ ", phoneNumber=" + phoneNumber + "\n"+ favoriteHotelsText() + "\n" + reservedRoomText() +
				"\n"+ searchHistoryText() + "\n" + searchHistoryText() + "]";
	}
	
	/**
	 * Method to add all the users to an ArrayList
	 * 
	 * @param lista - ArrayList where the users will be added
	 */
	public void arrayToArchive(ArrayList<User> lista){
		
		if(left != null) {
			User auxL = left;
			lista.add(auxL);
			left.arrayToArchive(lista);
		}
		
		if(right != null) {
			User auxR = right;
			lista.add(auxR);
			right.arrayToArchive(lista);
		}	
	}
	
	/**
	 * Method to add a search to the list
	 * 
	 * @param search - The search
	 */
	@Override
	public void addRecordFinal(String search) {
		SearchHistory searchT = new SearchHistory(search, null, null);
		
		if(record != null) {
			record.addRecord(searchT);
		}else {
			record = searchT;
		}
	}
	
	/**
	 * Method to delete the search history
	 */
	public void deleteRecord() {
		record = null;
	}
	
	public ArrayList<Room> arrayRooms(){
		ArrayList<Room> aux = new ArrayList<Room>();
		Room temp = rRooms;
		
		while(temp != null) {
			aux.add(temp);
			temp = temp.getNext();
		}
		
		return aux;
	}

}// final
