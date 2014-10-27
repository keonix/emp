package com.academysmart.repositorty;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.repository.EmployeeRepositorySingleton;

public class EmployeeRepositorySingletonTest {

	@BeforeClass
	public static void beforeClass()throws Exception {
		EmployeeRepositorySingleton.getRepository().addEmployee("Ivan",
			"Ivanov", "ivanov@mail.ru");
	}

	@Test
	public void testGetRepositoryReturnOneInstance() {
		Assert.assertSame(EmployeeRepositorySingleton.getRepository(),
				EmployeeRepositorySingleton.getRepository());
	}
	
	
	@Test(expected=IncorrectEmailException.class)
	public void testAddEmployeWithIncorrectEmail() throws ServletException  {
		EmployeeRepositorySingleton.getRepository().addEmployee("Ivan", "Ivanov", "ivanovICH@mail.ru");
		EmployeeRepositorySingleton.getRepository().addEmployee("Ivan", "Ivanov", "ivanovICH@mail.ru");
	}

}
