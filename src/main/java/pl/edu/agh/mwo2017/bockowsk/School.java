package pl.edu.agh.mwo2017.bockowsk;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class School implements java.io.Serializable {

	private long id;
	private String name;
	private String address;
	private Set<SchoolClass> classes;

	public School() {
	}

	public School(String name, String address) {
		super();
		this.name = name;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return "School: " + getName() + " (" + getAddress() + ")";
	}

	public Set<SchoolClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<SchoolClass> classes) {
		if (classes == null) {
			classes = new HashSet<SchoolClass>();
		}
		this.classes = classes;
	}

	protected void addClasses(SchoolClass sc) {
		if (classes == null ) {
			classes=new HashSet<SchoolClass>();
		}
		classes.add(sc);
	}

}
