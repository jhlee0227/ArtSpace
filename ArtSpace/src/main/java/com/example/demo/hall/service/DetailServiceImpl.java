package com.example.demo.hall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hall.dao.DetailDAO;
import com.example.demo.hall.dao.HallDAO;
import com.example.demo.hall.dto.HallAnswerDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallQuestionDTO;
import com.example.demo.mypage.dto.LikeDTO;

@Service
public class DetailServiceImpl implements DetailService {
	
	@Autowired
	DetailDAO detailDAO;
	@Autowired
	HallDAO hallDAO;

	@Override
	public void insertQuestion(HallQuestionDTO question) {
		detailDAO.insertQuestion(question);
	}

	@Override
	public void deleteQuestion(Integer question_id) {
		detailDAO.deleteQuestion(question_id);
	}

	@Override
	public void modifyQuestion(HallQuestionDTO question) {
		detailDAO.modifyQuestion(question);
	}

	@Override
	public void insertAnswer(HallAnswerDTO answer) {
		detailDAO.insertAnswer(answer);
	}

	@Override
	public void deleteAnswer(Integer answer_id) {
		detailDAO.deleteAnswer(answer_id);
		
	}

	@Override
	public void modifyAnswer(HallAnswerDTO answer) {
		detailDAO.modifyAnswer(answer);
	}

}
