package com.howtodoinjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import com.howtodoinjava.demo.service.EmployeeService;
import com.howtodoinjava.demo.service.impl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

	EmployeeService service = new EmployeeServiceImpl();
	@Test
	public void testAddService(){
		
		assertNotNull(service.addEmployee(10, 20));
		
		
		
	}
	
}
