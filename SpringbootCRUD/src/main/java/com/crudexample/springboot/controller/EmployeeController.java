package com.crudexample.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crudexample.springboot.exception.ResourceNotFoundException;
import com.crudexample.springboot.model.Employee;
import com.crudexample.springboot.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	


	@Autowired
	private EmployeeRepository employeeRepository; 
	
	//get employees
	
	@GetMapping("employees")
	public List<Employee> getAllEmployee(){
		return this.employeeRepository.findAll();
	}
	
	//get employee by id
	@GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
	
	@PostMapping("employees")
	//save employee
	public Employee createEmployee(@RequestBody Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	//update employee
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value ="id") Long employeeId,
			@Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		log.info("Hello"+employeeDetails);
		Employee employee = employeeRepository.findById(employeeId)
		          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		        
		        
		employee.setEmail(employeeDetails.getEmail());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		
		return ResponseEntity.ok(this.employeeRepository.save(employee));
	}
	
	//delete employee
	@DeleteMapping("employees/{id}")
	public Map<String,Boolean> deleteEmployee(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
		          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		this.employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}
	
	@PostMapping("/getJson")
	//convert Json to Map
	public void convertJson(@RequestBody Employee emp) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(emp);
		Map<String,Object> inputMap = mapper.readValue(json, Map.class);
		System.out.println(inputMap);
		System.out.println(inputMap.containsKey("firstName"));
		System.out.println(inputMap.containsValue(3));
	}
	
	/*
	  @PostMapping("/getJson")
	//convert Json to Map
	public void convertJson(@RequestBody Map<String,Object> inputMap) {
		System.out.println(inputMap.containsKey("firstName"));
		System.out.println(inputMap.containsValue(3));
	}*/
	
		
}
