package com.example.demo.file.dao;

import java.util.List;
import java.util.Map;

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

	public List<Integer> getImageList(Integer hall_id) {
		return sqlSession.selectList("hallFile.getImageList", hall_id);
	}

	public void deleteImages(List<Integer> fileIDList) {
		sqlSession.delete("hallFile.delete", fileIDList);
	}

	public void deleteAllImages(Integer hall_id) {
		sqlSession.delete("hallFile.deleteAllImages", hall_id);	
	}
	
	
	
}
