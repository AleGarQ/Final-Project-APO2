package model;

public class Person {

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
	
	public Person(String name, String id, String age) {
		this.name = name;
		this.id = id;
		this.age = age;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	

}
