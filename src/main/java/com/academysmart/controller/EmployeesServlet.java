package com.academysmart.controller;

import java.io.IOException;

import org.omg.CORBA.RepositoryIdHelper;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.repository.EmployeeRepositorySingleton;

@WebServlet("/employee.html")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//TODO implement logic to process GET requests
		getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
		request.setAttribute("employees", EmployeeRepositorySingleton.getRepository().getAllEmployees());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//TODO implement logic to process data that client sent to server with POST method.
		//It could include adding employee to repository,
		//validating email, redirecting client to a page where employee list is displayed etc.
			try{EmployeeRepositorySingleton.getRepository().addEmployee(request.getParameter("fname"), 
			request.getParameter("lname"), request.getParameter("email"));
	}catch(IncorrectEmailException e){
		String a = e.toString().substring(52);
		request.setAttribute("errMsg", a);
		//e.printStackTrace();
		} catch (com.academysmart.exception.ServletException e) {
		// TODO Auto-generated catch block
			String a = e.toString().substring(44);
			request.setAttribute("errMsg", a);
		//e.printStackTrace();
	}
	doGet(request, response);

	}
	
}
