package com.crudexample.springboot.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.crudexample.springboot.model.Employee;
import com.crudexample.springboot.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeMockitoTest {
	
	@Autowired
	private EmployeeService service;
	
	@MockBean
	private EmployeeRepository repository;
	
	@Test
	public void getEmployeesTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Employee("Jinal","bhl","jinal@bhl"), new Employee("Alka","sef","alka@sef"))
				.collect(Collectors.toList()));
		assertEquals(2, service.getEmployees().size());
	}
	
	@Test
	public void getEmployeeByNameTest() {
		String fname = "Jinal";
		when(repository.findByFirstName(fname)).thenReturn(new Employee("Jinal","bhl","jinal@bhl"));
		assertEquals("jinal@bhl", service.getEmployeeByName(fname).getEmail());
	}
	
	@Test
	public void saveEmployeeTest() {
		Employee employee = new Employee("Alka","sef","alka@sef");
		when(repository.save(employee)).thenReturn(employee);
		assertEquals(employee, service.addEmployee(employee));
	}
	
	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee("Alka","sef","alka@sef");
		service.deleteEmployee(employee);
		verify(repository, times(1)).delete(employee);
	}

}
