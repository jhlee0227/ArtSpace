package com.example.demo.admin.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminLoginDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	public int emailCheck(String email) {
		return sqlSession.selectOne("adminLogin.email_check", email);
	}

}
