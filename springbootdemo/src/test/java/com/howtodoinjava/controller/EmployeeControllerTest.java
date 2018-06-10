package com.howtodoinjava.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.howtodoinjava.demo.controller.EmployeeController;
import com.howtodoinjava.demo.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeControllerTest {
	
	@MockBean
	private EmployeeService employeeService;
	
	EmployeeController controller;
	
	@Before
	public void setUp(){
		
		controller = new EmployeeController();
		controller.setService(employeeService);
	}
	
	
	@Test
	public void getEmployeeTest(){
		
		Mockito.when(
				employeeService.addEmployee(Mockito.anyInt(), Mockito.anyInt())).thenReturn(20);
		
		String data = controller.getEmployees(10, 20, Mockito.anyString(), Mockito.anyString());
		System.out.println(" data :  "+data);
		assertNotNull(data);
	}

}
