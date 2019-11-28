package model;

public class Person {

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------
	
	/**
	 * Attribute that has the person's name
	 */
	private String name;
	/**
	 * Attribute that has the person's id
	 */
	private String id;
	/**
	 * Attribute that has the person's age
	 */
	private String age;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Constructor of the Person class
	 * @param name - Person's name
	 * @param id - Person's id
	 * @param age - Person's age
	 */
	public Person(String name, String id, String age) {
		this.name = name;
		this.id = id;
		this.age = age;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	
	/**
	 * Method to get the attribute name
	 * @return - name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the attribute name
	 * @param name - new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the attribute id
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method to set the attribute id
	 * @param id - new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Method to get the attribute age
	 * @return age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Method to set the attribute age
	 * @param age - new age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	

}
