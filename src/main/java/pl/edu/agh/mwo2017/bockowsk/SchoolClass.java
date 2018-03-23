package pl.edu.agh.mwo2017.bockowsk;

import java.util.HashSet;
import java.util.Set;

public class SchoolClass implements java.io.Serializable {

	private long id;
	private int startYear;
	private int currentYear;
	private String profile;
	private int school_id;

	private Set<Student> students;

	// KONSTRUKTORY:

	public SchoolClass(int startYear, int currentYear, String profile) {
		super();
		this.startYear = startYear;
		this.currentYear = currentYear;
		this.profile = profile;
	}

	public SchoolClass() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> studentsTemp) {
		if (students == null) {
			students = new HashSet<Student>();
		}
		this.students = studentsTemp;
	}

	public String toString() {
		return "Class: " + profile + " (Started: " + getStartYear() + ", Current year: " + getCurrentYear() + ")";
	}

	protected void addStudent(Student student) {
		students.add(student);
	}
}