package com.example.demo.mypage.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.user.dto.UserDTO;

@Repository
public class MypageDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	

	public UserDTO findById(Integer id) {
		return sqlSession.selectOne("mypage.findById", id);
	}

	public void updateNickname(UserDTO dto) {
		sqlSession.update("mypage.updateNickname", dto);
		
	}

	public void updatePw(UserDTO dto) {
		sqlSession.update("mypage.updatePw", dto);
		
	}

}
