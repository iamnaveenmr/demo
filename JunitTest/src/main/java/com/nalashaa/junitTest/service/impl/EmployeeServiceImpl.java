package com.nalashaa.junitTest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalashaa.junitTest.entity.Employee;
import com.nalashaa.junitTest.repository.IEmployeeRepository;
import com.nalashaa.junitTest.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	public List<Employee> findEmployee(String name) {
		// TODO Auto-generated method stub
		return employeeRepository.findByName(name);
	}
}
