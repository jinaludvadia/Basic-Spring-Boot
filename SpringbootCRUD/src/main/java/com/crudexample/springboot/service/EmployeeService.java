package com.crudexample.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.crudexample.springboot.model.Employee;
import com.crudexample.springboot.repository.EmployeeRepository;

import io.swagger.models.Response;

public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);


	public Employee addEmployee(Employee employee) {
		return repository.save(employee);
	}

	public List<Employee> getEmployees() {
		List<Employee> employees = repository.findAll();
		System.out.println("Getting data from DB : " + employees);
		return employees;
	}

	public Employee getEmployeeByName(String fname) {
		return repository.findByFirstName(fname);
	}

	public void deleteEmployee(Employee employee) {
		repository.delete(employee);
	}
}
