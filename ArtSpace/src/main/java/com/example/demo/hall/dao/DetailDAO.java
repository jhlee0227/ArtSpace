package com.example.demo.hall.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hall.dto.HallDTO;

@Repository
public class DetailDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public HallDTO findById(Integer id) {
		return sqlSession.selectOne("detailPage.findById", id);
	}

}
