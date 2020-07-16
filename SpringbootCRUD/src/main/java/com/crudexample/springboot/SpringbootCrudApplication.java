package com.crudexample.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.crudexample.springboot.controller.EmployeeController;

import io.swagger.models.Response;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootCrudApplication {
	
	
	private static final Logger log = LoggerFactory.getLogger(SpringbootCrudApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudApplication.class, args);
	}
	
}
