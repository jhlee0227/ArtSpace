package com.example.demo.hall.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hall.dto.HallDTO;

@Repository
public class HallDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public void insert(HallDTO hallDTO) {
		sqlSession.insert("hall.insert", hallDTO);
	}

	public HallDTO findById(Integer id) {
		return sqlSession.selectOne("hall.findById", id);
	}

}
