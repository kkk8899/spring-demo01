package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SpringHelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring!!";
	}

}
