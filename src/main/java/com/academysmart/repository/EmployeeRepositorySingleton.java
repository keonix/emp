package com.academysmart.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
			// throw new ServletException("Вы не заполнили необходимые поля");
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
