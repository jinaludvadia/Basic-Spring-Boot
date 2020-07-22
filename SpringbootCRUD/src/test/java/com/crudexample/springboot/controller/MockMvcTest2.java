package com.crudexample.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crudexample.springboot.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class MockMvcTest2 {
	
	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee = new Employee("Jinal","vgf","jinal@gmail.com");
		String json = mapper.writeValueAsString(employee);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
				.content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
			//	.andExpect(jsonPath("$.firstName", Matchers.is("Jinal")));
		String resultContent = result.getResponse().getContentAsString();
		Employee emp = mapper.readValue(resultContent, Employee.class);
		assertEquals("jinal@gmail.com", emp.getEmail());
	}
	
	@Test
	public void getAllEmployeeTest() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Employee[] emp = mapper.readValue(resultContent, Employee[].class);
		assertEquals("jinal@gmail.com", emp[2].getEmail());
	}
	
	

}
