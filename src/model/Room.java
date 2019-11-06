package model;

public class Room {

	public final static int INDIVIDUAL = 1;
	public final static int DOUBLE = 2;
	public final static int FAMILY = 3;
	public final static int MULTIPLE = 4;
	
	private String number;
	private String id;
	private int typeOfBeds;
	
	private Room previous;
	private Room next;
	
	public Room(String number, String id, int typeOfBeds) {
		super();
		this.number = number;
		this.id = id;
		this.typeOfBeds = typeOfBeds;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTypeOfBeds() {
		return typeOfBeds;
	}

	public void setTypeOfBeds(int typeOfBeds) {
		this.typeOfBeds = typeOfBeds;
	}
	
}
