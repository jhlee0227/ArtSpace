package com.example.demo.admin.dao;

import java.util.List;

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
		return sqlSession.selectList("admin.getAllUsers", )
	}

}
