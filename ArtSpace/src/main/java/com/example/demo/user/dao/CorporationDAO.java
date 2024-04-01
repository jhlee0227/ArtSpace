package com.example.demo.user.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.user.dto.CorporationDTO;
import com.example.demo.user.dto.UserDTO;


@Repository
public class CorporationDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	public void insertUser(UserDTO user) {
		sqlSession.insert("cor.insertUser", user);
	}
	
	public void insertCompanyNumber(CorporationDTO corpor) {
		sqlSession.insert("cor.insertCompanyNum", corpor);
	}

	public void updateUser(UserDTO userDTO) {		
		sqlSession.update("cor.updateUser", userDTO);		
	}

}
