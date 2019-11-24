package model;

import java.util.ArrayList;
import java.util.Comparator;

import exceptions.ExistentException;
import exceptions.ListNotFoundException;

public class User implements Comparator<User>, AddFavoriteHotelToTree {

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------

	/**
	 * Attribute that has the user's name
	 */
	private String name;
	/**
	 * Attribute that has the user's id
	 */
	private String id;
	/**
	 * Attribute that has the user's password
	 */
	private String password;
	/**
	 * Attribute that has the user's email
	 */
	private String email;
	/**
	 * Attribute that has the user's age
	 */
	private String age;
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
	public User(String name, String id, String password, String email, String age, String phoneNumber, User left,
			User right) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.left = left;
		this.right = right;
		customList = new ArrayList<CustomList>();
		sortListByName();
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Method to get the attribute name
	 * 
	 * @return User's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the attribute name
	 * 
	 * @param name - New User's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the attribute id
	 * 
	 * @return User's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method to set the attribute id
	 * 
	 * @param id - New User's id
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * Method to get the attribute age
	 * 
	 * @return User's age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Method to set the attribute age
	 * 
	 * @param age - New User's age
	 */
	public void setAge(String age) {
		this.age = age;
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
		if (aux == null) {
			chosenRoom.setAvailability(false);
			setRRooms(chosenRoom);
		} else {
			boolean added = false;
			while (aux != null && !added) {
				if (aux.getNext() == null) {
					aux.setNext(chosenRoom);
					chosenRoom.setAvailability(false);
					added = true;
				} else {
					aux = aux.getNext();
				}
			}
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
	
	public String favoriteHotelsText() {
		
		String toString = "";
		ArrayList<FavoriteHotel> epale = new ArrayList<>();
		
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
	
	public String reservedRoomText() {
		String toString = "";
		ReservedRoom aux = rRooms;
		
		while(aux != null) {
			toString += aux.toString() + ", ";
		}
		
		return toString;
	}
	
	public String customListText() {
		String toString = "";
		
		for(int i = 0; i < customList.size(); i++) {
			toString += customList.get(i).hotelsListedText() + ", ";
		}
		
		return toString;
	}
	
	public String searchHistoryText() {
		
		String toString = "";
		ArrayList<SearchHistory> epale = new ArrayList<>();
		
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
	
	@Override
	public String toString() {
		return "+User [name=" + name + ", id=" + id + ", password=" + password + ", email=" + email + ", age=" + age
				+ ", phoneNumber=" + phoneNumber + "\n"+ favoriteHotelsText() + "\n" + reservedRoomText() +
				"\n"+ searchHistoryText() + "\n" + searchHistoryText() + "]";
	}
	
	public void arrayToArchive(ArrayList<User> lista){
		
		if(left != null) {
			User auxL = left;
			auxL.setLeft(null);
			auxL.setRight(null);
			lista.add(auxL);
			left.arrayToArchive(lista);
		}
		if(right != null) {
			User auxR = right;
			auxR.setLeft(null);
			auxR.setRight(null);
			lista.add(auxR);
			right.arrayToArchive(lista);
		}	
	}
	
	public void addRecordFinal(String search) {
		SearchHistory searchT = new SearchHistory(search, null, null);
		
		if(record != null) {
			record.addRecord(searchT);
		}else {
			record = searchT;
		}
	}
}// final
