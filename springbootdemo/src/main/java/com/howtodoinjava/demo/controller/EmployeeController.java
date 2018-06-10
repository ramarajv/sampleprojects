package com.howtodoinjava.demo.controller;



import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howtodoinjava.demo.model.Employee;
import com.howtodoinjava.demo.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private static ObjectMapper MAPPER = new ObjectMapper();
	
	@Autowired
	private EmployeeService service;
	
	@GET
	@GetMapping("/students/{first}/{second}")
	//@Path("get/{storeNumber}/{articleNumber}")
    public String getEmployees(
			@PathVariable("first") Integer first, @PathVariable("second") Integer second, 
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName ) 
    {
		Integer sum = service.addEmployee(10, 20);
		Employee employee = new Employee(sum,firstName,lastName,"howtodoinjava@gmail.com");
		//MAPPER.writeValueAsString(employee);
		return employee.toString();
   
    }

	public EmployeeService getService() {
		return service;
	}

	public void setService(EmployeeService service) {
		this.service = service;
	}

}
