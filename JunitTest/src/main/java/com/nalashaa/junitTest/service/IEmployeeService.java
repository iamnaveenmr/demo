package com.nalashaa.junitTest.service;

import java.util.List;

import com.nalashaa.junitTest.entity.Employee;

public interface IEmployeeService {

	Employee save(Employee employee);
	
	List<Employee> findEmployee(String name);
	
}
