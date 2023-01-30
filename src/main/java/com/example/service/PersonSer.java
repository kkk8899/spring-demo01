package com.example.service;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.PersonModel;

@Service
public class PersonSer {

	@Autowired
	private PersonModel personModel;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private PrintStream ps = new PrintStream(System.out);

	public Map<String, Object> getAllPerson() {
		// TODO Auto-generated method stub
		System.out.println("getAllPerson開始 "+new Date().toLocaleString());
		System.out.println("取得Person全部會員資料..");
		Map<String, Object> rs=new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_person");
		rs.put("data",jdbcTemplate.queryForList(sql.toString()));

		List<Map<String, Object>> ms;
		ms = jdbcTemplate.queryForList(sql.toString());
		
		System.out.println(rs);
		System.out.println("getAllPerson結束 "+new Date().toLocaleString());
//		System.out.println(rs.get("data"));
//		System.out.println(ms);
//		for (Map<String, Object> i : ms) {
//			for (Map.Entry<String, Object> j : i.entrySet()) {
//				System.out.printf("ms Key->%s, Value->%S ", j.getKey(), j.getValue());
//			}
//		}
//		System.out.println("");
//		ms.stream().forEach(ls->{
//			ls.entrySet().forEach(ll->{
//				System.out.printf("ms Key->%s, Value->%S ", ll.getKey(), ll.getValue());
//			});
//		});
//		System.out.println("");
//		for (Entry<String, Object> keys : rs.entrySet()) {
//		   System.out.println(keys.getKey()+":"+keys.getValue());
//		}
//		rs.forEach((k, v)->System.out.printf("rs Key->%s, Value->%S ", k, v));
//		System.out.println("");
//		System.out.println("name:"+ms.toString());

		return rs;
	}

	public Map<String, Object> getOnePerson(String id) {
		// TODO Auto-generated method stub
		System.out.println("getPerson開始 "+new Date().toLocaleString());
		System.out.println("取得Person : "+id+" 會員資料..");
		Map<String, Object> rs=new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_person where ID = "+id);
		rs.put("data",jdbcTemplate.queryForList(sql.toString()));
		
		List<Map<String, Object>> ms;
		ms = jdbcTemplate.queryForList(sql.toString());
		
		System.out.println(rs);
		System.out.println("getPerson結束 "+new Date().toLocaleString());
		
		return rs;
	}

	public void addPerson(String name, String nickname, String sex, String birthday, String description) throws Exception{
		Date date = new Date();
		List<Map<String, Object>> ii = jdbcTemplate.queryForList("select max(id)+1 as id from tb_person");
		Object iid = ii.get(0).get("id");
		String sql = "INSERT INTO tb_person ("
				+ "name, nickname, sex, birthday, description, create_time)"
				+ "values(?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[] {name, nickname, sex, birthday, description, date};
		try {
			jdbcTemplate.update(sql, params);
			ps.println(sql+params);
			personModel.setId(iid.toString());
			selPerson();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> selPerson() {
		// TODO Auto-generated method stub
		System.out.println("selPerson開始 "+new Date().toLocaleString());
		System.out.println("取得ID "+personModel.getId()+" Person會員資料..");
		Object[] params = new Object[]{personModel.getId()};

		List<Map<String, Object>> rs=new ArrayList<Map<String, Object>>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_person WHERE ID = ?");
		if (params != null) {
			ps.println(sql+":"+params[0].toString());
		}else {
			ps.println(sql+":"+params.toString());
		}
		
		rs = jdbcTemplate.queryForList(sql.toString(), params);
		for (Map<String, Object> i : rs) {
			for (Map.Entry<String, Object> j : i.entrySet()) {
				System.out.printf("rs Key->%s, Value->%S ", j.getKey(), j.getValue());
				switch (j.getKey()) {
				case "id":
					if (j.getValue()==null) {
						break;
					}
					personModel.setId(j.getValue().toString());
					break;
				case "name":
					if (j.getValue()==null) {
						break;
					}
					personModel.setName(j.getValue().toString());
					break;
				case "nickname":
					if (j.getValue()==null) {
						break;
					}
					personModel.setNickname(j.getValue().toString());
					break;
				case "sex":
					if (j.getValue()==null) {
						break;
					}
					personModel.setSex(j.getValue().toString());
					break;
				case "birthday":
					if (j.getValue()==null) {
						break;
					}
					personModel.setBirthday(j.getValue().toString());
					break;
				case "description":
					if (j.getValue()==null) {
						break;
					}
					personModel.setDescription(j.getValue().toString());
					break;
				case "create_time":
					if (j.getValue()==null) {
						break;
					}
					personModel.setCreate_time(j.getValue().toString());
					break;
				default:
					break;
				}
			}
		}
		
		System.out.println(rs);
		System.out.println("selPerson結束 "+new Date().toLocaleString());

		return rs;
	}

	public void editPerson(String id, String name, String nickname, String sex, String birthday, String description) throws Exception{
		Date date = new Date();
		String iid = personModel.getId();
		String sql = "UPDATE tb_person SET "
				+ "name = ?, nickname = ?, sex = ?, birthday = ?, description = ?, create_time = ? "
				+ "WHERE id = " + id;
		Object[] params = new Object[] {name, nickname, sex, birthday, description, date};
		
		try {
			jdbcTemplate.update(sql, params);
			ps.println(sql+params);
			selPerson();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
