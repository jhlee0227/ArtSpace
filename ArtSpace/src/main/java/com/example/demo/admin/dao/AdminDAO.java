package com.example.demo.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.user.dto.UserDTO;

@Repository
public class AdminDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("admin.getAllUsers");
	}

	public void leave(Integer user_id) {

		sqlSession.update("admin.leave", user_id);
	}

	public void resign(Integer user_id) {

		sqlSession.update("admin.resign", user_id);
	}

//	public List<UserDTO> searchUsersByNickname(String keyword) {
//		Map<String, String> param = new HashMap<>();
//		param.put("keyword", keyword);
//		return sqlSession.selectList("admin.searchUsersByNickname", param);
//	}
//	
//	public List<UserDTO> searchUsersByEmail(String keyword) {
//		Map<String, String> param = new HashMap<>();
//		param.put("keyword", keyword);
//		return sqlSession.selectList("admin.searchUsersByEmail", param);
//	}
}
