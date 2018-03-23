package pl.edu.agh.mwo2017.bockowsk;

public class Student implements java.io.Serializable {
	// id INTEGER PRIMARY KEY, name TEXT, surname TEXT, class_id BIGINT REFERENCES schoolClasses (id), pesel TEXT
	// PROPERTIES
	private long id;
	private String name;
	private String surname;
	// mapping here
	private int class_id;
	private String pesel;

	// CONSTUCTORS
	public Student() {
		super();
	}

	// GETTERS & SETTERS
	
	public Student(String name, String surname, String pesel) {
		super();
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	// HELPERS
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", class_id=" + class_id + ", pesel="
				+ pesel + "]";
	}
	

	

	
	
}
