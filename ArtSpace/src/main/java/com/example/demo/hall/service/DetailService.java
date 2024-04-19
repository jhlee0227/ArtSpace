package com.example.demo.hall.service;

import com.example.demo.hall.dto.HallAnswerDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallQuestionDTO;

public interface DetailService {
	
	public void insertQuestion(HallQuestionDTO question);

	public void deleteQuestion(Integer question_id);

	public void modifyQuestion(HallQuestionDTO question);

	public void insertAnswer(HallAnswerDTO answer);

	public void deleteAnswer(Integer answer_id);

	public void modifyAnswer(HallAnswerDTO answer);

}
