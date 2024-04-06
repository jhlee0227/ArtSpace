package com.example.demo.announcement.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.announcement.dto.AskDto;

@Repository
public class AskDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public void insert(AskDto askDTO) {
		sqlSession.insert("ask.insertask", askDTO);
	}

	public List<AskDto> getAsk() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("ask.getAsk");
	}
}
