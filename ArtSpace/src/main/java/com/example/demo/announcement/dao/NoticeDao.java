package com.example.demo.announcement.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.announcement.dto.NoticeDto;
import com.example.demo.user.dto.UserDTO;

@Repository
public class NoticeDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public void insert(NoticeDto noticeDto) {
		sqlSession.insert("notice.insertboard", noticeDto);
	}

	public List<NoticeDto> getNotice() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("notice.getNotice");
	}

	
//	public BoardDto login(BoardDto boardDto) {
//		return sqlSession.selectOne("user.login", boardDto);
//	}
//
//	public int emailCheck(String email) {
//		return sqlSession.selectOne("user.email_check", email);
//	}

}
