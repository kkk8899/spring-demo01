package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.PersonService;

@RestController
public class HelloSpring {

	@Autowired
	PersonService personService;

	@RequestMapping("/hello")
	public String HelloSpring() {
		return "Hello Spring Boot!";
	}

//	@GetMapping("/Person")
//	/* 列出資料Api */
//	public Object getPerson(ModelMap model) {
//		// 取得基本資料
//		Map<String, Object> rs=new HashMap<String, Object>();
//		rs = personService.getAllPerson();
//		model.addAttribute("name", "測試");
//		model.addAttribute("list", rs);
//
//		return rs;
//	}
}
