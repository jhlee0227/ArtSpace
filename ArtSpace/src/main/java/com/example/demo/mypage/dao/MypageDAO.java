package com.example.demo.mypage.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mypage.dto.MypageDTO;

@Repository
public class MypageDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	

	public MypageDTO findById(Integer id) {
		return sqlSession.selectOne("mypage.findById", id);
	}

	public void updateNickname(MypageDTO dto) {
		sqlSession.update("mypage.updateNickname", dto);
		
	}

}
