package com.nalashaa.junitTest.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nalashaa.junitTest.entity.Employee;
import com.nalashaa.junitTest.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class.getName());

	@Autowired
	IEmployeeService employeeService;

	@RequestMapping(value = "/save", method = { RequestMethod.POST }, consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody Employee employee) throws Exception {
		logger.info("Entered - EmployeeController/save");
		ResponseEntity<?> responseEntity = null;
		Employee savedEmployee = null;
		try {
			if (employee != null) {
				savedEmployee = employeeService.save(employee);
				responseEntity = new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
			} else {
				logger.error("Data in Employee save to be incomplete ");
				responseEntity = new ResponseEntity<Employee>(savedEmployee, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception ex) {
			logger.error("Exception :  EmployeeController/ save ", ex);
			logger.error("Error Message : " + ex.getMessage());
			throw new Exception();
		}
		logger.info("Exiting - EmployeeController/save");
		return responseEntity;
	}
	
	@RequestMapping(value = "/getByName/{name}", method = { RequestMethod.GET })
	public ResponseEntity<?> fetchEmployee(@PathVariable String name) throws Exception {
		logger.info("Entered - EmployeeController/fetchEmployee");
		ResponseEntity<?> responseEntity = null;
		List<Employee> employeeList = null;
		try {
			if (!StringUtils.isEmpty(name)) {
				employeeList = employeeService.findEmployee(name);
				responseEntity = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
			} else {
				logger.error("Data to fetch Employee to be incomplete ");
				responseEntity = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception ex) {
			logger.error("Exception :  EmployeeController/ fetchEmployee ", ex);
			logger.error("Error Message : " + ex.getMessage());
			throw new Exception();
		}
		logger.info("Exiting - EmployeeController/fetchEmployee");
		return responseEntity;
	}
	
}
