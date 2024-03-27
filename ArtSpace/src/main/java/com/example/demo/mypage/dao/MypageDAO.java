package com.example.demo.mypage.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mypage.dto.PerformerDTO;
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

	public PerformerDTO findByPID(Integer id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mypage.findByPID", id);
	}


	public void insertPerformer(PerformerDTO dto) {
		sqlSession.insert("mypage.insertPerformer", dto);
	}

//	public void updatePerformer(PerformerDTO dto) {
//		sqlSession.update("mypage.updatePerformer", dto);
//	}

}
