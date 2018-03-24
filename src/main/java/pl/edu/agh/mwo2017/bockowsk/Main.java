package pl.edu.agh.mwo2017.bockowsk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		// main.printSchools();
		// main.jdbcTest();
		// main.printSchools();
		// main.addNewData();
		// Cwiczenie 6 - przygotowane do sprawdzenia
		main.addNewData2();
		main.close();

	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}

	private void printSchools() {
		Criteria crit = session.createCriteria(School.class);
		List<School> schools = crit.list();

		System.out.println("### Schools and classes");
		for (School s : schools) {
			System.out.println(s);
			for (SchoolClass c : s.getClasses()) {
				System.out.println("\t" + c);
				// students
				for (Student student : c.getStudents()) {
					System.out.println("\t\t" + student);
				}
			}
		}
	}

	private void addNewData() {
		Student student1 = new Student("Tomasz", "Salmon", "12023099345");
		Student student2 = new Student("Pawel", "Rys", "12023345999");
		Student student3 = new Student("Piotr", "Bor", "12023076134");
		SchoolClass klasa1 = new SchoolClass(2016, 2018, "Ekonomia");
		klasa1.addStudent(student1);
		klasa1.addStudent(student2);
		klasa1.addStudent(student3);
		School szkola = new School("UE", "Rakowicka 99");
		szkola.addClasses(klasa1);
		Transaction transaction = session.beginTransaction();
		session.save(szkola);
		transaction.commit();
	}

	private void addNewData2() {
		Teacher teacher1=new Teacher("Marek", "Gajecki", "dr", "52210298675");
		Teacher teacher2=new Teacher("Aleksander", "Byrski", "dr", "58210255675");
		String hql = "FROM SchoolClass as classes";
		Query query = session.createQuery(hql);
		List<SchoolClass> results = query.list();
		for (SchoolClass s : results) {
			System.out.println("dodaje: "+s+" do: "+teacher1);
			s.addTeacher(teacher1);
			System.out.println("dodaje: "+s+" do: "+teacher2);
			s.addTeacher(teacher2);
		}
		Transaction transaction = session.beginTransaction();
		session.save(teacher1);
		session.save(teacher2);
		transaction.commit();

	}

	private void executeQueries() {
		String hql = "FROM School";
		Query query = session.createQuery(hql);
		List results = query.list();
		System.out.println(results);

	}

	private void executeQuery1() {
		String hql = "FROM School as school WHERE school.name='UE'";
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		for (School s : results) {
			System.out.println(s);
		}
	}

	private void executeQuery2() {
		String hql = "FROM School as school WHERE school.name='UE'";
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		for (School s : results) {
			System.out.println("DRY RUN: session.delete(s)");
		}
	}

	private int executeQuery3() {
		List<Object> wynik = session
				.createSQLQuery("SELECT count(school.name),school.name from schools as school GROUP BY school.name")
				.list();
		int result = wynik.size();
		return result;
	}

	private int executeQuery4() {
		String hql = "FROM Student as students";
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		int howMany = results.size();
		return howMany;

	}

	private void executeQuery5() {
		List<Integer> wynik = session.createSQLQuery(
				"SELECT schools.id,count(schoolClasses.profile) as ile FROM schools,schoolClasses WHERE schools.id=schoolClasses.school_id GROUP BY schools.name HAVING ile >= 2")
				.addScalar("id", Hibernate.INTEGER).list();
		System.out.println("==> szkoly majace wiecej niz 2 klasy: ");
		for (Integer s : wynik) {
			String hql = "FROM School as schools WHERE schools.id=" + s.intValue();
			Query query = session.createQuery(hql);
			List<School> schools = query.list();
			for (School sh : schools) {
				System.out.println(sh);
			}
		}
	}

	private void executeQuery6() {
		String hql = "SELECT s FROM School as s INNER JOIN s.classes classes WHERE classes.profile='mat-fiz' AND classes.currentYear >= 2";
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		for (School s : results) {
			System.out.println(s);
		}
	}

	private void jdbcTest() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("org.sqlite.JDBC");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/school.db", "", "");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM schools";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("name");
				String address = rs.getString("address");

				// Display values
				System.out.println("Name: " + name);
				System.out.println(" address: " + address);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end jdbcTest

}
