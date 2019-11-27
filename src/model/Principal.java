package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
		try {
			loadUsers();
		}catch(ExistentException e) {
			e.getMessage();
		}catch(UnderAge e) {
			e.getMessage();
		}
		serializeHotelsAndRooms();
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
		if (newUser != null) {
			String[] ageA = newUser.getAge().split("/");
			int age = Integer.parseInt(ageA[2]);

			if (2019 - age >= 18) {
				if(users == null) {
					users = newUser;
				}else {
					if (searchUser(newUser, users) == false) {
						addNewUser(newUser, users);
					} else {
						throw new ExistentException("El correo ingresado ya está registrado");
					}
				}	
			} else {
				throw new UnderAge("No esta permitido crear cuentas a un menor de edad");
			}
		} else {
			throw new NullPointerException("El usuario nuevo está vacío");
		}
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
			throw new ListNotFoundException("No se ha encontrado ninguna lista con ese nombre");
		}

		return found1;
	}

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
	
	public ArrayList<Hotel> searchHotelsByCity(String city) {
		ArrayList<Hotel> perCity = new ArrayList<>();
		for (int i = 0; i < hotels.size(); i++) {
			if (hotels.get(i).getCity().equalsIgnoreCase(city)) {
				perCity.add(hotels.get(i));
			}
		}
		return perCity;
	}

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

	public ArrayList<User> arrayToText() {
		ArrayList<User> usersArray = new ArrayList<>();

		if (users != null) {
			User auxP = users;
			usersArray.add(auxP);
			users.arrayToArchive(usersArray);
		}

		return usersArray;
	}

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
	
	public void generateUserArchive2() {
		try {
			File file = new File("files/Users");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < arrayToText().size(); i++) {
				bw.write(arrayToText().get(i).toString());
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	public void addReservedRoom(ReservedRoom nueva, User node) {
		if(idActual.equals(node.getId())) {
			node.addReservedRoom(nueva);
		}else if(idActual.compareTo(node.getId()) < 0) {
			addReservedRoom(nueva, node.getLeft());
		}else {
			addReservedRoom(nueva, node.getRight());
		}
	}
	
	
	public void addReservedRoomFinal(ReservedRoom nueva) {
		if(users != null) {
			addReservedRoom(nueva, users);
		}
	}
	
	public void reserveRoom(String hotelName, String idRoom) {
		
		boolean ya = false;
		for(int i = 0; i < hotels.size() && !ya; i++) {
			if(hotels.get(i).getName().equals(hotelName)) {
				Room aux = hotels.get(i).reserveRoom(idRoom);
				ya = true;
				ReservedRoom temp = new ReservedRoom(aux.getNumber(), aux.getId(), aux.getTypeOfBeds(), aux.getAvailability(), aux.getHotel(), null, null);
				addReservedRoomFinal(temp);
			}
		}
	}
	
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
	
	public void addFavoriteRoom2(String idHotel, User node) throws ExistentException {
		if(idActual.equals(node.getId())) {
			node.addNewFavoriteHotelFinal(addFavoriteRoom1(idHotel));
		}else if(idActual.compareTo(node.getId()) < 0) {
			addFavoriteRoom2(idHotel, node.getLeft());
		}else {
			addFavoriteRoom2(idHotel, node.getRight());
		}
	}
	
	public void addFavoriteRoomFinal(String idHotel) throws ExistentException {
		if(users != null) {
			addFavoriteRoom2(idHotel, users);
		}
	}
	
	public void createCustomList1(String listName, User node) {
		if(idActual.equals(node.getId())) {
			node.createNewCustomList(listName);
		}else if(idActual.compareTo(node.getId()) < 0) {
			createCustomList1(listName, node.getLeft());
		}else {
			createCustomList1(listName, node.getRight());
		}
	} 
	
	public void createCustomListFinal(String listName) {
		if(users != null) {
			createCustomList1(listName, users);
		}
	}
	
	public void addRecord(String search, User node) {
		if(idActual.equals(node.getId())) {
			node.addRecordFinal(search);
		}else if(idActual.compareTo(node.getId()) < 0) {
			addRecord(search, node.getLeft());
		}else {
			addRecord(search, node.getRight());
		}
	}
	
	public void addRecordFinal(String search) {
		if(users != null) {
			addRecord(search, users);
		}
	}
	
	public void addHotelToCustomList(String listName, HotelsListed newHotelToList, User node) throws ListNotFoundException {
		if(idActual.equals(node.getId())) {
			node.addHotelToCustomList(listName, newHotelToList);
		}else if(idActual.compareTo(node.getId()) < 0) {
			addHotelToCustomList(listName, newHotelToList, node.getLeft());
		}else {
			addHotelToCustomList(listName, newHotelToList, node.getRight());
		}
	}
	
	public void addHotelToCustomListFinal(String idHotel, String listName) throws ListNotFoundException {
		if(listName != null) {
			Hotel temp = addFavoriteRoom1(idHotel);
			HotelsListed toAdd = new HotelsListed(temp.getName(), temp.getId(), temp.getPriceRange(), temp.getStars(), temp.getScore(), temp.getCity());
			if(users != null) {
				addHotelToCustomList(listName, toAdd, users);
			}
		}
	}
	
	public void deleteRecord(User node) {
		if(idActual.equals(node.getId())) {
			node.deleteRecord();
		}else if(idActual.compareTo(node.getId()) < 0) {
			deleteRecord(node.getLeft());
		}else {
			deleteRecord(node.getRight());
		}
	}
	
	public void deleteRecordFinal() {
		if(users != null) {
			deleteRecord(users);
		}
	}
}// final
