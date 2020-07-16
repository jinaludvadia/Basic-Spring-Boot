package com.crudexample.springboot.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crudexample.springboot.SpringbootCrudApplication;
import com.crudexample.springboot.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCrudApplication.class)
class EmployeeRepositoryTest {
	
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeRepositoryTest.class);

	@Autowired
	EntityManager em;
		
	@Autowired
	EmployeeRepository repository;	
	
	@Test
	public void findByIdEmployeePresent() {
		Optional<Employee> employee = repository.findById(4l);
		assertTrue(employee.isPresent());
	}
	
	@Test
	public void findByIdEmployeeNotPresent() {
		Optional<Employee> employee = repository.findById(3l);
		assertFalse(employee.isPresent());
	}
	
	@Test
	public void findAllEmployees() {
		log.info("Employees -> {}",repository.findAll());
		log.info("Count -> {}",repository.count());
	}
	
	@Test
	public void findEmployeeByNameTest() {
		Employee employee = repository.findByFirstName("Jinal");
        assertNotNull(employee);
		
	}
	
	@Test
	public void getEmployeeTest() {
		Employee employee = new Employee("Jinal","fcf","jinal@gmail.com");
		//repository.save(employee);
		Employee employee2 = repository.findByFirstName("Jinal");
        assertNotNull(employee2);
        assertEquals(employee.getFirstName(),employee2.getFirstName());
        assertEquals(employee.getLastName(),employee2.getLastName());
        assertEquals(employee.getEmail(),employee2.getEmail());
	}
	
	@Test
	public void insertEmployeeTest() {
		Employee employee = new Employee("Neel","sgwj","neel@gmail.com");
		repository.save(employee);
		Employee employee2 = repository.findByFirstName("Neel");
        assertNotNull(employee2);
        assertEquals(employee.getFirstName(),employee2.getFirstName());
        assertEquals(employee.getLastName(),employee2.getLastName());
        assertEquals(employee.getEmail(),employee2.getEmail());
	}
	
	
	@Test
	public void updateEmployeeTest() {
		Employee employee = repository.findByFirstName("Neel");
        employee.setLastName("hggh");
        repository.save(employee);
        Employee employee2 = repository.findByFirstName("Neel");
		repository.save(employee2);
        assertEquals(employee.getLastName(),employee2.getLastName());
	}
	
	@Test
	public void deleteEmployeeTest() {
		Employee employee = repository.findByFirstName("Neel");
		repository.delete(employee);
		Employee employee2 = repository.findByFirstName("Neel");
		assertNull(employee2);
	}

}
