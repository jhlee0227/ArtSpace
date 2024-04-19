package com.example.demo.hall.dto;

import java.time.LocalDateTime;

public class HallQuestionDTO {

	private Integer question_id;
	private String content;
	private Integer user_id;
	private Integer hall_id;
	private LocalDateTime create_date;
	
	private String nickname;
	private String email;
	
	// 답변은 질문당하나
	private HallAnswerDTO answer;
	
	
	public Integer getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public LocalDateTime getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public HallAnswerDTO getAnswer() {
		return answer;
	}
	public void setAnswer(HallAnswerDTO answer) {
		this.answer = answer;
	}
	
	
	
	
}
