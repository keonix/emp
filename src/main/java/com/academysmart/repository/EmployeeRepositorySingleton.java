package com.academysmart.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import com.academysmart.controller.EmployeesServlet;
import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.model.Employee;

public class EmployeeRepositorySingleton {

	public List<Employee> employmentlist = new LinkedList<Employee>();
	int id = 1;
	public static EmployeeRepositorySingleton instance;

	// public EmployeeRepositorySingleton() {
	// Employee emp = new Employee();
	// emp.setId(0);
	// emp.setFname("");
	// emp.setLname("");
	// emp.setEmail("");
	// this.employmentlist.add(emp);
	// }

	public static EmployeeRepositorySingleton getRepository() {
		// TODO implement method that returns repository instance
		if (instance == null) {
			synchronized (EmployeeRepositorySingleton.class) {
				if (instance == null) {
					instance = new EmployeeRepositorySingleton();
				}
			}
		}
		return instance;
	}

	public int addEmployee(String fname, String lname, String email)
			throws ServletException {
	
		// TODO implement method that adds an employee to repository
		// This method should check that email is not used by other employees
		String user = "Ladyshinskyi";
		String password = "121234512";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";// URL àäðåñ
		String driver = "oracle.jdbc.driver.OracleDriver";// Èìÿ äðàéâåðà
		try {
		Class.forName(driver);// Ðåãèñòðèðóåì äðàéâåð
		Locale.setDefault(Locale.ENGLISH); 
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		Connection c = null;// Ñîåäèíåíèå ñ ÁÄ
		try {
		c = DriverManager.getConnection(url, user, password);// Óñòàíîâêà
		// ñîåäèíåíèÿ
		// ñ ÁÄ
		Statement st = (Statement) c.createStatement();// Ãîòîâèì çàïðîñ
	
		ResultSet rs = st.executeQuery("select * from  Employee");// Âûïîëíÿåì
		// çàïðîñ "insert into EMPLOYEES values('2', 'Olya', 'Chevychelova','chevy@ru'
		// ê ÁÄ,
		// ðåçóëüòàò
		// â
		// ïåðåìåííîé
		// rs
		while (rs.next()) {
		System.out.println(rs.getString(1));
		}
		
		 PreparedStatement  preperedStatement ;
		 preperedStatement = c.prepareStatement("insert into employee "
				 + "values(?, ?, ?, ?)");
				 preperedStatement.setInt(1, id);
				 preperedStatement.setString(2, fname);
				 preperedStatement.setString(3, lname);
				 preperedStatement.setString(4, email);
				 preperedStatement.executeUpdate();
		 
		 
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		// Îáÿçàòåëüíî íåîáõîäèìî çàêðûòü ñîåäèíåíèå
		try {
		if (c != null)
		c.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		//--------------------------------
		int nextOne = 0;

		if (!checkExistingEmail(employmentlist, email)
		// &&!checkIsEmplty(employmentlist, fname, lname, email)
		) {
			nextOne = 1;

			
			Employee emp = new Employee();
			emp.setId(id);

			emp.setFname(fname);

			emp.setLname(lname);

			emp.setEmail(email);
			if (checkIsEmplty(fname, lname, email)) {
				throw new ServletException("Заполните все поля");
			}

			// if (emp.getFname().equals("") || emp.getLname().equals("")
			// || emp.getEmail().equals("")) {
			// throw new ServletException("Âû íå çàïîëíèëè íåîáõîäèìûå ïîëÿ");
			// }
			this.employmentlist.add(emp);
			id++;
		}

		return nextOne;
	}

	private boolean checkExistingEmail(List<Employee> employmentlist,
			String email) throws IncorrectEmailException {
		boolean check = false;
		ListIterator<Employee> listIterator = employmentlist.listIterator();
		while (listIterator.hasNext()) {
			if (listIterator.next().getEmail().equals(email)) {
				check = true;
				throw new IncorrectEmailException("Пользователь с"
+ " таким email уже введен в базу");
				// break;

			}

		}
		return check;
	}

	private boolean checkIsEmplty(String fname, String lname, String email)
			throws ServletException {
		boolean check = false;
		if (fname.isEmpty() || lname.isEmpty() || email.isEmpty()) {
			check = true;

			// break;

		}

		return check;
	}

	public List<Employee> getAllEmployees() {
		// TODO implement method that returns list of all employees
		return employmentlist;
	}

}
