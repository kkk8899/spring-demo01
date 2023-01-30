package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.PersonModel;
import com.example.service.PersonSer;

@Controller
public class PersonCon {

	@Autowired
	PersonModel personModel;
	
	@Autowired
	PersonSer personSer;

	@GetMapping("/Person")
	public Object getPerson(Model model) {
		// 取得基本資料
		Map<String, Object> rs=new HashMap<String, Object>();
		rs = personSer.getAllPerson();
		model.addAttribute("name", "這是名字");
		model.addAttribute("list", rs);

		return "Person";
	}

	@GetMapping("/addForm")
	public Object form(Model model) {
		personModel = new PersonModel();
		model.addAttribute("person", personModel);
		return "addForm";
	}

	@PostMapping("/addPerson")
	public Object addPerson(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "nickname", defaultValue = "") String nickname,
			@RequestParam(value = "sex", defaultValue = "") String sex,
			@RequestParam(value = "birthday", defaultValue = "") String birthday,
			@RequestParam(value = "description", defaultValue = "") String description,
			Model model) {
		try {
			System.out.println(name+" "+nickname+" "+sex+" "+birthday+" "+description);
			personSer.addPerson(name, nickname, sex, birthday, description);
			model.addAttribute("person", personModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addPerson";
	}

	@GetMapping("/updateForm")
	public Object updateForm(
			@RequestParam(value = "qid", defaultValue = "") String id,
			Model model) {
		try {
//			System.out.println(id);
			personModel.setId(id);
			personSer.selPerson();
			model.addAttribute("person", personModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "editForm";
	}
	
	@GetMapping("/editForm")
	public Object editForm(Model model) {
		try {
			model.addAttribute("person", personModel);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "editForm";
	}

	@PostMapping("/editPerson")
	public Object editPerson(
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "nickname", defaultValue = "") String nickname,
			@RequestParam(value = "sex", defaultValue = "") String sex,
			@RequestParam(value = "birthday", defaultValue = "") String birthday,
			@RequestParam(value = "description", defaultValue = "") String description,
			Model model) {
		try {
			System.out.println(name+" "+nickname+" "+sex+" "+birthday+" "+description);
			personSer.editPerson(id, name, nickname, sex, birthday, description);
			model.addAttribute("person", personModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addPerson";
	}

}
