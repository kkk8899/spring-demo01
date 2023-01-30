package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.dmeo", "com.example.controller", "com.example.model", "com.example.repository", "com.example.service"})
@EntityScan({"com.example.dmeo", "com.example.controller", "com.example.model", "com.example.repository", "com.example.service"})
public class SpringDemo01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemo01Application.class, args);
	}

}
