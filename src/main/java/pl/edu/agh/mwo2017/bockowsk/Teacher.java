package pl.edu.agh.mwo2017.bockowsk;

import java.util.HashSet;
import java.util.Set;

public class Teacher {
// PROPERTIES
	private long id;
	private String name;
	private String surname;
	private String title;
	private String pesel;

	private Set<SchoolClass> charges;

// CONSTRUCTORS
	public Teacher(String name, String surname, String title, String pesel) {
		this();
		this.name = name;
		this.surname = surname;
		this.title = title;
		this.pesel = pesel;
	}

	public Teacher() {
		super();
	}
// GETTERS & SETTERS

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Set<SchoolClass> getLectures() {
		return charges;
	}

	public void setLectures(Set<SchoolClass> lectures) {
		this.charges = lectures;
	}
	
	public void addCharges(SchoolClass schoolClass) {
		if (charges == null) {
			charges=new HashSet<SchoolClass>(); 
		}
		charges.add(schoolClass);
	}
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", surname=" + surname + ", title=" + title + ", pesel=" + pesel
				+ ", lectures=" + charges + "]";
	}
	
// HELPERS
	
	
}
