package com.howtodoinjava.demo.service.impl;

import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	public Integer addEmployee(Integer a, Integer b) {
		
		return a+b;
	}

}
