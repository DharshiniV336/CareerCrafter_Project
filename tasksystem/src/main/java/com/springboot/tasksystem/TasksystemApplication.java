package com.springboot.tasksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class TasksystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksystemApplication.class, args);
	}

}
