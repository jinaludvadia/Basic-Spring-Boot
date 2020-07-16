package com.crudexample.springboot;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;

import com.crudexample.springboot.controller.EmployeeController;
import com.crudexample.springboot.exception.ResourceNotFoundException;
import com.crudexample.springboot.model.Employee;
import com.crudexample.springboot.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapExample {
	
	private static final Logger log = LoggerFactory.getLogger(JsonMapExample.class);

	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, ResourceNotFoundException {
		
		String json = "{\"id\":1,\"firstName\":\"Jinal\",\"lastName\":\"drd\", \"email\":\"jinal@gmail.com\"}";
		
		ObjectMapper mapper = new ObjectMapper();
		//convert JSON to Map
		Map<String, String> map;
		map = mapper.readValue(json, Map.class);
		//print JSON 
		System.out.println(map);
				
	}
	
}
