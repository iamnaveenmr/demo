package com.nalashaa.junitTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nalashaa.junitTest.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findByName(String name);

}
