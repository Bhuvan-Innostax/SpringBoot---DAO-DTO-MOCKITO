package com.innostax.employee.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.autoconfigure.domain.EntityScan;  

@SpringBootApplication
@EntityScan(basePackages = "com.innostax.employee.Entity")
@ComponentScan(basePackages = "com.innostax.employee") 
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
		System.out.println("Project is Running . . . ");
	}

}
