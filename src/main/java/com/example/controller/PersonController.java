package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.PersonModel;
import com.example.service.PersonService;

@Controller
@RequestMapping("/Demo")
public class PersonController {

	@Autowired
	PersonModel personModel;

	@Autowired
	PersonService personService; 

	@GetMapping("/Person")
	/* 列出資料Api */
	public Object getPerson(ModelMap model) {
		// 取得基本資料
		Map<String, Object> rs=new HashMap<String, Object>();
		rs = personService.getAllPerson();
		model.addAttribute("name", "測試");
		model.addAttribute("list", rs);

		return "Person";
	}

	@GetMapping("/addForm")
	/* 新增資料頁面 */
	public Object addForm(ModelMap model) {
		//personModel = new PersonModel();
		/* 另一種方式 */
		personModel.setId("");
		personModel.setName("");
		personModel.setNickname("");
		personModel.setSex("");
		personModel.setBirthday("");
		personModel.setDescription("");
		personModel.setCreate_time("");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addForm");
		mv.addObject("person", personModel);
		//model.addAttribute("person", personModel);
		return mv;
	}

	@PostMapping("/addPerson")
	/* 新增資料儲存Api */
	public Object addPerson(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "nickname", defaultValue = "") String nickname,
			@RequestParam(value = "sex", defaultValue = "") String sex,
			@RequestParam(value = "birthday", defaultValue = "") String birthday,
			@RequestParam(value = "description", defaultValue = "") String description,
			ModelMap model) {
		try {
			System.out.println(name+" "+nickname+" "+sex+" "+birthday+" "+description);
			personService.addPerson(name, nickname, sex, birthday, description);

			model.addAttribute("person", personModel);
//			personSer.selPerson();
			System.out.println(personModel.getId()+" "+personModel.getName()+" "
							+personModel.getNickname()+" "+personModel.getSex()+" "
							+personModel.getBirthday()+" "+personModel.getDescription()+" "+personModel.getCreate_time());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "addPerson";
	}

	@GetMapping("/updateForm")
	/* 修改資料 取得ID */
	public Object updateForm(
			@RequestParam(value = "qid", defaultValue = "") String id,
			ModelMap model) {
		try {
//			System.out.println(id);
			personModel.setId(id);
			personService.selPerson();
			model.addAttribute("person", personModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "editForm";
	}
	
	@GetMapping("/editForm")
	/* 修改資料頁面 */
	public Object editForm(ModelMap model) {
		try {
			model.addAttribute("person", personModel);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "editForm";
	}

	@PostMapping("/editPerson")
	/* 修改資料儲存Api */
	public Object editPerson(
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "nickname", defaultValue = "") String nickname,
			@RequestParam(value = "sex", defaultValue = "") String sex,
			@RequestParam(value = "birthday", defaultValue = "") String birthday,
			@RequestParam(value = "description", defaultValue = "") String description,
			ModelMap model) {
		try {
			System.out.println(name+" "+nickname+" "+sex+" "+birthday+" "+description);
			personService.editPerson(id, name, nickname, sex, birthday, description);
			model.addAttribute("person", personModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		return "editPerson";
	}

	@PostMapping("/delPerson")
	/* 刪除資料Api */
	public Object delPerson(
			@RequestParam(value = "qid", defaultValue = "")String id,
			ModelMap model) {
		try {
			System.out.println(new Date()+"刪除資料:"+id);
			personService.delPerson(id);
			model.addAttribute("list", personService.getAllPerson());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		}
		return "Person";
	}

}
