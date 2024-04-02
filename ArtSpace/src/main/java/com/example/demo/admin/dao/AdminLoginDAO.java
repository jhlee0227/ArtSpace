package com.example.demo.admin.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.admin.dto.AdminDTO;

@Repository
public class AdminLoginDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	public int emailCheck(String email) {
		return sqlSession.selectOne("adminLogin.email_check", email);
	}

	public void insert(AdminDTO adminDTO) {

		sqlSession.insert("adminLogin.insert", adminDTO);
		
	}

	public AdminDTO login(AdminDTO adminDTO) {
		return sqlSession.selectOne("adminLogin.login", adminDTO);
	}

}
