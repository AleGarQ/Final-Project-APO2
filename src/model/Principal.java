package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import exceptions.ExistentException;
import exceptions.ListNotFoundException;
import exceptions.UnderAge;
import exceptions.WrongInformation;

public class Principal implements AddUserToTree {

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------

	/**
	 * Actual user's ID
	 */
	private String idActual;
	/**
	 * Relation with the users
	 */
	private User users;
	/**
	 * Relation with the hotels
	 */
	private ArrayList<Hotel> hotels;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor of the Principal class
	 */
	public Principal() {
		hotels = new ArrayList<Hotel>();
//		init();
		loadHotels();
		addRoom();
		try {
			loadUsers();
		}catch(ExistentException e) {
			e.getMessage();
		}catch(UnderAge e) {
			e.getMessage();
		}
		serializeHotelsAndRooms();
		generateUserArchive();
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	public void init() {
		FavoriteHotel fhAux = new FavoriteHotel("Marriot", "1007707024", "150000", 5, 4.7, "Cali", null, null);
		users.setfHotel(fhAux);

		SearchHistory shAux = new SearchHistory("Sandra Rueda en bola", null, null);
		users.setRecord(shAux);

		HotelsListed hlAux = new HotelsListed("Marriot", "1007707024", "150000", 5, 4.7, "Cali");
		CustomList clAux = new CustomList("Los más perrenques", hlAux);
		ArrayList<CustomList> listica = new ArrayList<>();
		listica.add(clAux);
		users.setCustomList(listica);

		Hotel hotel = new Hotel("Marriot", "1007707024", "150000", 5, 4.7, "Cali");
		Room roomi = new Room("A1", "101", Room.DOUBLE, false, hotel.getName());
		hotel.setRooms(roomi);
		hotels.add(hotel);
		
		ReservedRoom rrAux = new ReservedRoom("A1", "101", Room.DOUBLE, false, hotel.getName(), null, null);
		users.setRRooms(rrAux);

	//	generateUserArchive();
	}

	/**
	 * Method to get the attribute idActual
	 * 
	 * @return ID of the actual user
	 */
	public String getIdActual() {
		return idActual;
	}

	/**
	 * Method to set the attribute idActual
	 * 
	 * @param idActual - New idActual
	 */
	public void setIdActual(String idActual) {
		this.idActual = idActual;
	}

	/**
	 * Method to get the relation users
	 * 
	 * @return The relation with the users
	 */
	public User getUsers() {
		return users;
	}

	/**
	 * Method to set the relation users
	 * 
	 * @param users - New user
	 */
	public void setUsers(User users) {
		this.users = users;
	}

	/**
	 * Method to get the relation hotels
	 * 
	 * @return The relation with the hotels
	 */
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	/**
	 * Method to set the relation hotels
	 * 
	 * @param hotels - New hotel
	 */
	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

	/**
	 * Previous method to add an user without check the root
	 * 
	 * @param newUser - New user
	 * @param node    - Root of the tree
	 */

	@Override
	public void addNewUser(User newUser, User node) {
		if (newUser.getEmail().compareTo(node.getEmail()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(newUser);
			} else {
				addNewUser(newUser, node.getLeft());
			}
		} else {
			if (node.getRight() == null) {
				node.setRight(newUser);
			} else {
				addNewUser(newUser, node.getRight());
			}
		}
	}

	/**
	 * Method to search an user in the tree
	 * 
	 * @param newUser - New user
	 * @param node    - Root of the tree
	 */

	@Override
	public boolean searchUser(User newUser, User node) {

		if (node != null) {
			if (newUser.getEmail().compareTo(node.getEmail()) == 0) {
				return true;
			} else if (newUser.getEmail().compareTo(node.getEmail()) < 0) {
				return searchUser(newUser, node.getLeft());
			} else {
				return searchUser(newUser, node.getRight());
			}
		}
		return false;
	}

	/**
	 * Method to add an user checking the root
	 * 
	 * @param newUser - New user
	 * @throws ExistentUser - Exception if the email is registered
	 * @throws UnderAge     - Exception if the new user is younger
	 */

	@Override
	public void addNewUserFinal(User newUser) throws ExistentException, UnderAge {
		if(newUser.getName().equals("") || newUser.getId().equals("") || newUser.getPassword().equals("") 
				|| newUser.getEmail().equals("") || newUser.getPhoneNumber().equals("")) {
			throw new NullPointerException("Alguno de los campos requeridos se encuentra vacio");
		}
		
		String[] ageA = newUser.getAge().split("/");
		int age = Integer.parseInt(ageA[2]);
		
		if (2019 - age >= 18) {
			if (users == null) {
				users = newUser;
			} else {
				if (searchUser(newUser, users) == false) {
					addNewUser(newUser, users);
				} else {
					throw new ExistentException("El correo ingresado ya está registrado");
				}
			}
		} else {
			throw new UnderAge("No esta permitido crear cuentas a un menor de edad");
		}
		setIdActual(newUser.getId());
	}

	/**
	 * Method to let sign in or not to a user
	 * 
	 * @param email    - User's email
	 * @param password - User's password
	 * @param node     - Node to use recursion in the tree
	 */
	public boolean signIn(String email, String password, User node) {
		if (node != null) {
			if (email.compareTo(node.getEmail()) == 0 && password.compareTo(node.getPassword()) == 0) {
				return true;
			} else if (email.compareTo(node.getEmail()) < 0 && password.compareTo(node.getPassword()) < 0) {
				return signIn(email, password, node.getLeft());
			} else {
				return signIn(email, password, node.getRight());
			}
		}
		return false;
	}

	/**
	 * Method to let sign in or not to a user
	 * 
	 * @param email    - User's email
	 * @param password - User's password
	 * @throws WrongInformation - Exception if the user is not found
	 * @return The user that was found / Null
	 */
	public User signInFinal(String email, String password) throws WrongInformation {
		User actual;

		if (signIn(email, password, users) == false) {
			throw new WrongInformation("El correo o clave ingresados son incorrectos");
		} else {
			actual = findUser(email, users);
		}
		
		setIdActual(actual.getId());

		return actual;
	}

	/**
	 * Method to find a user with the email
	 * 
	 * @param email - User's email
	 * @param node  - Node to use recursion in the tree
	 * @return The user that was found / Null
	 */
	public User findUser(String email, User node) {

		if (node != null) {
			if (email.compareTo(node.getEmail()) == 0) {
				return node;
			} else if (email.compareTo(node.getEmail()) < 0) {
				return findUser(email, node.getLeft());
			} else {
				return findUser(email, node.getRight());
			}
		} else {
			return null;
		}

	}

	/**
	 * Method to sort the hotel's list by name
	 */
	public void alphabeticalSortHotels() {
		for (int i = 1; i < hotels.size(); i++) {
			Hotel aux = hotels.get(i);
			for (int j = i; j > 0 && hotels.get(j - 1).getName().compareTo(aux.getName()) > 0; j--) {
				Hotel temp = hotels.get(j);
				hotels.set(j, hotels.get(j - 1));
				hotels.set(j - 1, temp);
			}
		}
	}

	/**
	 * Method to sort the hotel's list by score
	 */
	public void sortHotelsByScore() {
		for (int i = 0; i < hotels.size() - 1; i++) {
			int min = i;
			for (int j = i + 1; j < hotels.size(); j++) {
				if (hotels.get(j).getScore() < hotels.get(min).getScore()) {
					min = j;
				}
			}
			if (i != min) {
				Hotel aux = hotels.get(i);
				hotels.set(i, hotels.get(min));
				hotels.set(min, aux);
			}
		}
	}

	/**
	 * Method to sort the hotel's list by price range
	 */
	public void sortHotelsByPriceRange() {
		for (int i = 0; i < hotels.size() - 1; i++) {
			int min = i;
			for (int j = i + 1; j < hotels.size(); j++) {
				if (hotels.get(j).getPriceRange().compareTo(hotels.get(min).getPriceRange()) < 0) {
					min = j;
				}
			}
			if (i != min) {
				Hotel aux = hotels.get(i);
				hotels.set(i, hotels.get(min));
				hotels.set(min, aux);
			}
		}
	}

	/**
	 * Method to sort the hotel's list by city
	 */
	public void sortHotelsByCity() {
		for (int i = 1; i < hotels.size(); i++) {
			Hotel aux = hotels.get(i);
			for (int j = i; j > 0 && hotels.get(j - 1).getCity().compareTo(aux.getCity()) > 0; j--) {
				Hotel temp = hotels.get(j);
				hotels.set(j, hotels.get(j - 1));
				hotels.set(j - 1, temp);
			}
		}
	}

	/**
	 * Method to sort the hotel's list by stars
	 */
	public void sortHotelsByStars() {
		for (int i = 1; i < hotels.size(); i++) {
			Hotel aux = hotels.get(i);
			for (int j = i; j > 0 && hotels.get(j - 1).getStars() > aux.getStars(); j--) {
				Hotel temp = hotels.get(j);
				hotels.set(j, hotels.get(j - 1));
				hotels.set(j - 1, temp);
			}
		}
	}

	/**
	 * Method to search hotels with rooms to reserve
	 * 
	 * @return ArrayList with the hotels
	 */
	public ArrayList<Hotel> searchHotelsWithAvaiblesRoom() {
		ArrayList<Hotel> avaibleHotels = new ArrayList<Hotel>();
		for (int i = 0; i < hotels.size(); i++) {
			Hotel auxH = hotels.get(i);
			Room auxR = auxH.getRooms();
			Boolean stopPoint = false;
			while (auxR != null && !stopPoint) {
				if (auxR.getAvailability() == true) {
					avaibleHotels.add(auxH);
					stopPoint = true;
				} else {
					auxR.getNext();
				}
			}
		}
		return avaibleHotels;
	}

	/**
	 * Method to search an hotel by name
	 * 
	 * @param hotelName - Hotel's name
	 * @throws ListNotFoundException - Exception if the list is not found
	 * @return The hotel that was found / Null
	 */
	public Hotel searchHotelByName(String hotelName) throws ListNotFoundException {
		Hotel found1 = null;

		boolean found = false;
		int start = 0;
		int end = hotels.size() - 1;

		while (start <= end && !found) {
			int half = (start + end) / 2;
			if (hotels.get(half).getName().compareTo(hotelName) == 0) {
				found = true;
				found1 = hotels.get(half);
			} else if (hotels.get(half).getName().compareTo(hotelName) > 0) {
				end = half - 1;
			} else {
				start = half + 1;
			}
		}
		if (found == false) {
			throw new ListNotFoundException("No se ha encontrado ningun hotel con ese nombre");
		}

		return found1;
	}

	/**
	 * Method to search an hotel by id
	 * 
	 * @param hotelName - Hotel's id
	 * @throws ListNotFoundException - Exception if the list is not found
	 * @return The hotel that was found / Null
	 */
	public Hotel searchHotelById(String hotelId) throws ListNotFoundException {
		Hotel found1 = null;

		boolean found = false;
		int start = 0;
		int end = hotels.size() - 1;

		while (start <= end && !found) {
			int half = (start + end) / 2;
			if (hotels.get(half).getId().compareTo(hotelId) == 0) {
				found = true;
				found1 = hotels.get(half);
			} else if (hotels.get(half).getId().compareTo(hotelId) > 0) {
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
	 * Method to search hotels by city
	 * 
	 * @param city - Hotel's city
	 * @return An ArrayList with the hotels that were found / Null
	 */
	public ArrayList<Hotel> searchHotelsByCity(String city) {
		ArrayList<Hotel> perCity = new ArrayList<Hotel>();
		for (int i = 0; i < hotels.size(); i++) {
			if (hotels.get(i).getCity().equalsIgnoreCase(city)) {
				perCity.add(hotels.get(i));
			}
		}
		return perCity;
	}

	/**
	 * Method to serialize hotels and rooms
	 */
	public void serializeHotelsAndRooms() {
		try {
			File f = new File("files/Hotels and Rooms");
			FileOutputStream fo = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fo);

			oos.writeObject(hotels);
			oos.close();

		} catch (IOException e) {
		}
	}

	/**
	 * Method to create an ArrayList with the users
	 * 
	 * @return ArrayList with the users
	 */
	public ArrayList<User> arrayToText() {
		ArrayList<User> usersArray = new ArrayList<User>();

		if (users != null) {
			User auxP = users;
			usersArray.add(auxP);
			users.arrayToArchive(usersArray);
		}

		return usersArray;
	}

	/**
	 * Method to generate an archive with the information of all users
	 */
	public void generateUserArchive() {
		ArrayList<User> usuarios = arrayToText();
		try {
			PrintWriter pw = new PrintWriter("files/Users");

			for (int i = 0; i < usuarios.size(); i++) {
				pw.write(usuarios.get(i).toString());
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to load hotels from a CSV archive
	 */
	public void loadHotels() {
		try {
			File file = new File("files/Hotelsfile.csv");
			
			if(file.exists()) {
				FileReader fileR = new FileReader(file);
				BufferedReader br = new BufferedReader(fileR);
				String line = br.readLine();
				
				while(line != null) {
					String parts[] = line.split(",");
					String name = parts[0];
					String id = parts[1];
					String priceRange = parts[2];
					int stars = Integer.parseInt(parts[3]);
					double score = Double.parseDouble(parts[4]);
					String city = parts[5];

					Hotel nuevesito = new Hotel(name, id, priceRange, stars, score, city);
					hotels.add(nuevesito);

					line = br.readLine();
				}
				br.close();
				fileR.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to load users from a CSV archive
	 * @throws ExistentException
	 * @throws UnderAge
	 */
	public void loadUsers() throws ExistentException, UnderAge {
		
		try {
			File file = new File("files/Usersfile.csv");
			
			if(file.exists()) {
				FileReader fileR = new FileReader(file);
				BufferedReader br = new BufferedReader(fileR);
				String line = br.readLine();
				
				while(line != null) {
					String parts[] = line.split(",");
					String name = parts[0];
					String id = parts[1];
					String age = parts[2];
					String password = parts[3];
					String email = parts[4];
					String phoneNumber = parts[5];
						
					User epale = new User(name, id, age, password, email, phoneNumber, null, null);
					addNewUserFinal(epale);
						
					line = br.readLine();
				}
				br.close();
				fileR.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to add an hotel to the list of reserve
	 * 
	 * @param nueva - New ReservedRoom
	 * @param node - Node to do recursion
	 */
	public void addReservedRoom(ReservedRoom nueva, User node) {
		if(idActual.equals(node.getId())) {
			node.addReservedRoom(nueva);
			
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				addReservedRoom(nueva, node.getLeft());
				
			}	
		}else {
			if(node.getRight() != null) {
				addReservedRoom(nueva, node.getRight());
			}
		}
	}
	
	/**
	 * Method to add a room to the reserve list
	 * 
	 * @param nueva - New ReservedRoom
	 */
	public void addReservedRoomFinal(ReservedRoom nueva) {
		if(users != null) {
			addReservedRoom(nueva, users);
			
		}
	}
	

	/**
	 * Method to reserve a room
	 * 
	 * @param hotelID - Hotel's ID
	 * @param idRoom - Room's ID
	 */
	public Room reserveRoom(String hotelID, String idRoom) {
		Room actual = null;
		boolean ya = false;
		for(int i = 0; i < hotels.size() && !ya; i++) {
			if(hotels.get(i).getId().equals(hotelID)) {
				Room aux = hotels.get(i).reserveRoom(idRoom);
				ya = true;
				ReservedRoom temp = new ReservedRoom(aux.getNumber(), aux.getId(), aux.getTypeOfBeds(), false, aux.getHotel(), null, null);
				actual = temp;
				addReservedRoomFinal(temp);
			}
		}
		return actual;
	}
	
	/**
	 * Method to add an HOTEL to the favorite list
	 * 
	 * @param idHotel - Hotel's ID
	 * @return The hotel to add
	 */
	public Hotel addFavoriteRoom1(String idHotel) {
		boolean ya = false;
		Hotel temp = null;
		for(int i = 0; i < hotels.size() && !ya; i++) {
			if(hotels.get(i).getId().equals(idHotel)) {
				ya = true;
				temp = hotels.get(i);
			}
		}
		return temp;
	}
	
	/**
	 * Method to add an HOTEL to the favorite list
	 * 
	 * @param idHotel - Hotel's ID
	 * @param node - Node to do recursion
	 * @throws ExistentException - It's throw if the hotel was already added
	 */
	public void addFavoriteRoom2(String idHotel, User node) throws ExistentException {
		if(idActual.equals(node.getId())) {
			node.addNewFavoriteHotelFinal(addFavoriteRoom1(idHotel));
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				addFavoriteRoom2(idHotel, node.getLeft());
			}		
		}else {
			if(node.getRight() != null) {
				addFavoriteRoom2(idHotel, node.getRight());
			}	
		}
	}
	
	/**
	 * Method to add an HOTEL to the favorite list
	 * @param idHotel - Hotel's ID
	 * @throws ExistentException - It's throw if the hotel was already added
	 */
	public void addFavoriteRoomFinal(String idHotel) throws ExistentException {
		if(users != null) {
			addFavoriteRoom2(idHotel, users);
		}
	}
	
	/**
	 * Method to create a custom list
	 * @param listName - Name of the new custom list
	 * @param node - Node to do recursion
	 */
	public  ArrayList<CustomList> createCustomList1(String listName, User node, ArrayList<CustomList> lists) {

		if(idActual.equals(node.getId())) {
			node.createNewCustomList(listName);
			lists = node.getCustomList();
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				createCustomList1(listName, node.getLeft(), lists);
			}
		}else {
			if(node.getRight() != null) {
				createCustomList1(listName, node.getRight(), lists);
			}
		}
		return lists;
	} 
	

	/**
	 * Method to create a custom list
	 * @param listName - Name of the new custom list
	 */
	public ArrayList<CustomList> createCustomListFinal(String listName) {
		ArrayList<CustomList> lists = new ArrayList<CustomList>();
		if(users != null) {
			lists = createCustomList1(listName, users, lists);
		}
		System.out.println(lists.size());
		return lists;
	}
	
	/**
	 * Method to add a record
	 * @param search - Text that has been searched
	 * @param node - Node to do recursion
	 */
	public void addRecord(String search, User node) {
		if(idActual.equals(node.getId())) {
			node.addRecordFinal(search);
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				addRecord(search, node.getLeft());
			}
		}else {
			if(node.getRight() != null) {
				addRecord(search, node.getRight());
			}
		}
	}
	
	/**
	 * Method to add a record
	 * @param search - Text that has been searched
	 */
	public void addRecordFinal(String search) {
		if(users != null) {
			addRecord(search, users);
		}
	}
	
	/**
	 * Method to add an hotel to a custom list
	 * @param listName - Name of the custom list where the hotel will be added
	 * @param newHotelToList - Hotel to add
	 * @param node - Node to do recursion
	 * @throws ListNotFoundException - Is throw if the list doesn't exist
	 */
	public void addHotelToCustomList(String listName, HotelsListed newHotelToList, User node) throws ListNotFoundException {
		if(idActual.equals(node.getId())) {
			node.addHotelToCustomList(listName, newHotelToList);
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				addHotelToCustomList(listName, newHotelToList, node.getLeft());
			}
		}else {
			if(node.getRight() != null) {
				addHotelToCustomList(listName, newHotelToList, node.getRight());
			}
		}
	}
	
	/**
	 * Method add an hotel to a custom list
	 * @param idHotel - Hotel's ID
	 * @param listName - Name of the custom list where the hotel will be added
	 * @throws ListNotFoundException - Its throw if the custom list doesn't exists
	 */
	public void addHotelToCustomListFinal(String idHotel, String listName) throws ListNotFoundException {
		if(listName != null) {
			Hotel temp = addFavoriteRoom1(idHotel);
			HotelsListed toAdd = new HotelsListed(temp.getName(), temp.getId(), temp.getPriceRange(), temp.getStars(), temp.getScore(), temp.getCity());
			if(users != null) {
				addHotelToCustomList(listName, toAdd, users);
			}
		}
	}
	
	/**
	 * Method to delete the search history
	 * @param node - Node to do recursion
	 */
	public void deleteRecord(User node) {
		if(idActual.equals(node.getId())) {
			node.deleteRecord();
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				deleteRecord(node.getLeft());
			}
		}else {
			if(node.getRight() != null) {
				deleteRecord(node.getRight());
			}
		}
	}
	
	/**
	 * Method to delete the search history
	 */
	public void deleteRecordFinal() {
		if(users != null) {
			deleteRecord(users);
		}
	}
	
	/**
	 * Method to add rooms to hotels
	 */
	public void addRoom() {
		for(int i = 0; i < hotels.size(); i++) {
			String nHotel = hotels.get(i).getName();
			Room aux1 = new Room("A1", "101", Room.INDIVIDUAL, true, nHotel);
			Room aux2 = new Room("A2", "102", Room.INDIVIDUAL, true, nHotel);
			Room aux3 = new Room("A3", "103", Room.INDIVIDUAL, true, nHotel);
			Room aux4 = new Room("A4", "104", Room.INDIVIDUAL, true, nHotel);
			Room aux5 = new Room("A5", "105", Room.INDIVIDUAL, true, nHotel);
			Room aux6 = new Room("B1", "201", Room.DOUBLE, true, nHotel);
			Room aux7 = new Room("B2", "202", Room.DOUBLE, true, nHotel);
			Room aux8 = new Room("B3", "203", Room.DOUBLE, true, nHotel);
			Room aux9 = new Room("B4", "204", Room.DOUBLE, true, nHotel);
			Room aux10 = new Room("B5", "205", Room.DOUBLE, true, nHotel);
			Room aux11 = new Room("C1", "301", Room.FAMILY, true, nHotel);
			Room aux12 = new Room("C2", "302", Room.FAMILY, true, nHotel);
			Room aux13 = new Room("C3", "303", Room.FAMILY, true, nHotel);
			Room aux14 = new Room("C4", "304", Room.FAMILY, true, nHotel);
			Room aux15 = new Room("C5", "305", Room.FAMILY, true, nHotel);
			Room aux16 = new Room("D1", "401", Room.MULTIPLE, true, nHotel);
			Room aux17 = new Room("D2", "402", Room.MULTIPLE, true, nHotel);
			Room aux18 = new Room("D3", "403", Room.MULTIPLE, true, nHotel);
			Room aux19 = new Room("D4", "404", Room.MULTIPLE, true, nHotel);
			Room aux20 = new Room("D5", "405", Room.MULTIPLE, true, nHotel);
			hotels.get(i).addRoom(aux20);
			hotels.get(i).addRoom(aux19);
			hotels.get(i).addRoom(aux18);
			hotels.get(i).addRoom(aux17);
			hotels.get(i).addRoom(aux16);
			hotels.get(i).addRoom(aux15);
			hotels.get(i).addRoom(aux14);
			hotels.get(i).addRoom(aux13);
			hotels.get(i).addRoom(aux12);
			hotels.get(i).addRoom(aux11);
			hotels.get(i).addRoom(aux10);
			hotels.get(i).addRoom(aux9);
			hotels.get(i).addRoom(aux8);
			hotels.get(i).addRoom(aux7);
			hotels.get(i).addRoom(aux6);
			hotels.get(i).addRoom(aux5);
			hotels.get(i).addRoom(aux4);
			hotels.get(i).addRoom(aux3);
			hotels.get(i).addRoom(aux2);
			hotels.get(i).addRoom(aux1);
		}
	}
	
	/**
	 * Method to create an ArrayList of rooms
	 * @param idHotel
	 * @return
	 */
	public ArrayList<Room> arrayRooms(String idHotel){
		ArrayList<Room> aux = null;
		boolean ya = false;
		
		for(int i = 0; i < hotels.size() && !ya; i++) {
			if(hotels.get(i).getId().equals(idHotel)){
				ya = true;
				aux = hotels.get(i).arrayRooms();
			}
		}
		return aux;
	}
	
	public User findUserById(String id, User node) {
		if (node != null) {
			
			if (id.compareTo(node.getId()) == 0) {
				return node;
			} else if (id.compareTo(node.getId()) < 0) {
				if(node.getLeft() != null) {
				return findUserById(id, node.getLeft());
				}
			} else {
				if(node.getRight() != null) {
				return findUserById(id, node.getRight());
				}
			}
		}
		return null;

	}
	
	public ArrayList<Room> arrayReservedRooms(String idUser){
		ArrayList<Room> aux = new ArrayList<Room>();
		boolean ya = false;
		if((idUser.equals(""))==false) {
			User newU = findUserById(idUser, users);
			if(newU != null && !ya) {
				
				ya = true;
				aux = newU.arrayRooms();
			}
			
		}
		return aux;
	}
	

	/**
	 * Method to change an user's name
	 * @param name1 - New name
	 * @param node - node to do recursion
	 */
	public void changeName(String name1,  User node) {
		if(idActual.equals(node.getId())) {
			node.setName(name1);
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				changeName(name1, node.getLeft());
			}
		}else {
			if(node.getRight() != null) {
				changeName(name1, node.getRight());
			}
		}
	}
	
	/**
	 * Method to change user's email
	 * @param email1 - New email
	 * @param node - Node to do recursion
	 */
	public void changeEmail(String email1,  User node) {
		if(idActual.equals(node.getId())) {
			node.setEmail(email1);
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				changeEmail(email1, node.getLeft());
			}
		}else {
			if(node.getRight() != null) {
				changeEmail(email1, node.getRight());
			}
		}
	}
	
	/**
	 * Method to change the user's phone
	 * @param phone - New phone
	 * @param node - Node to do recursion
	 */
	public void changePhone(String phone,  User node) {
		if(idActual.equals(node.getId())) {
			node.setPhoneNumber(phone);
		}else if(idActual.compareTo(node.getId()) < 0) {
			if(node.getLeft() != null) {
				changePhone(phone, node.getLeft());
			}
		}else {
			if(node.getRight() != null) {
				changePhone(phone, node.getRight());
			}
		}
	}
	
	/**
	 * Method to change the user's data
	 * @param name1 - New name
	 * @param email1 - New email
	 * @param phone - New phone
	 */
	public void changeDataFinal(String name1, String email1, String phone) {
		if(users != null) {
			if(name1 != null && (name1.equals("")==false)) {
				changeName(name1, users);
			}
			if(email1 != null && (email1.equals("")==false)) {
				changeEmail(email1, users);
			}
			if(phone != null && (phone.equals("")==false)) {
				changePhone(phone, users);
			}
		}
	}
}// final
