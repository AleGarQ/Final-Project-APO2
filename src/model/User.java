package model;

import java.util.Comparator;

public class User implements Comparator<User>{

	private String name;
	private String id;
	private String password;
	private String email;
	private String age;
	private String phoneNumber;
	
	private User left;
	private User right; 
	private ReservedRoom rRooms;
	private FavoriteRoom fRooms;
	private CustomList customList;
	private SearchHistory record;
	
	public User(String name, String id, String password, String email, String age, String phoneNumber) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getLeft() {
		return left;
	}

	public void setLeft(User left) {
		this.left = left;
	}

	public User getRight() {
		return right;
	}

	public void setRight(User right) {
		this.right = right;
	}

	@Override
	public int compare(User u1, User u2) {
		int obtainedNumber = u1.getName().compareTo(u2.getName());
		return obtainedNumber;
	}

}
