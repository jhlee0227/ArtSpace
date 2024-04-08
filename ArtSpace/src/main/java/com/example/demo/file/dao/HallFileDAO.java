package com.example.demo.file.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hall.dto.HallImageDTO;

@Repository
public class HallFileDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 공연장 이미지 넣기
	public void insertHallImage(HallImageDTO hallImage) {
		sqlSession.insert("hallFile.image_insert", hallImage);
	}
}
