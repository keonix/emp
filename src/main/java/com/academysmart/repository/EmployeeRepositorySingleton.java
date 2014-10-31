package com.academysmart.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import com.academysmart.controller.EmployeesServlet;
import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.model.Employee;

public class EmployeeRepositorySingleton {
	public static List<Employee> employmentlist = new LinkedList<Employee>();
	static int id =1;
	public static EmployeeRepositorySingleton instance;
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String user = "Ladyshinskyi";
	static String url = "jdbc:oracle:thin:@localhost:1521/xe";
	static String password = "121234512";
static Connection connect;
static Connection c;
	
	public   static void  getAllEmployeeWhenStartDB() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				try {
			connect = DriverManager.getConnection(url, user, password);
			Statement statement =  connect.createStatement();
					
			ResultSet 	choiseResult = statement
					.executeQuery("SELECT * FROM Employee ORDER BY id");
			while (choiseResult.next()) {
				Employee newEmployee = new Employee();
				newEmployee.setId(choiseResult.getInt(1));
				newEmployee.setFname(choiseResult.getString(2));
				newEmployee.setLname(choiseResult.getString(3));
				newEmployee.setEmail(choiseResult.getString(4));
				employmentlist.add(newEmployee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

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
	//Statement statement = null;
	
	
		try {
			Class.forName(driver);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	Connection c = null;
		try {
			Connection	c = DriverManager.getConnection(url, user, password);// Óñòàíîâêà
	
			Statement statement =c.createStatement();
			
			ResultSet	choiseResult = statement.executeQuery("SELECT * FROM EMPLOYEE ORDER BY id");
			while (choiseResult.next()) {
			id = 1 + Integer.parseInt(choiseResult.getString("id"));
			}
			Statement st = (Statement) c.createStatement();// Ãîòîâèì çàïðîñ
			ResultSet rs = st.executeQuery("select * from EMPLOYEE ");// Âûïîëíÿåì
		
		
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			PreparedStatement preperedStatement;
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
			
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// --------------------------------
		int nextOne = 0;
		if (!checkExistingEmail(employmentlist, email)
	
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
		}
		return check;
	}

	public List<Employee> getAllEmployees() {
		// TODO implement method that returns list of all employees
			
		
		return employmentlist;
	}

	
}
