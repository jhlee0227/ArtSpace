package com.example.demo.hall.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hall.dto.HallAnswerDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallQuestionDTO;

@Repository
public class DetailDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public HallDTO findById(Integer id) {
		return sqlSession.selectOne("detailPage.findById", id);
	}

	public void insertQuestion(HallQuestionDTO question) {
		sqlSession.insert("detailPage.insertQuestion", question);
	}

	public List<HallQuestionDTO> getQuestionList(Integer hall_id){
		return sqlSession.selectList("detailPage.getQuestionList", hall_id);
	}

	public void deleteQuestion(Integer question_id) {
		sqlSession.delete("detailPage.deleteQuestion", question_id);
	}

	public void modifyQuestion(HallQuestionDTO question) {
		sqlSession.update("detailPage.modifyQuestion", question);
	}

	public void insertAnswer(HallAnswerDTO answer) {
		sqlSession.insert("detailPage.insertAnswer", answer);
	}
	
	public HallAnswerDTO getAnswer(Integer question_id){
		return sqlSession.selectOne("detailPage.getAnswer", question_id);
	}

	public void deleteAnswer(Integer answer_id) {
		sqlSession.delete("detailPage.deleteAnswer", answer_id);
		
	}

	public void modifyAnswer(HallAnswerDTO answer) {
		sqlSession.update("detailPage.modifyAnswer", answer);
	}
}
