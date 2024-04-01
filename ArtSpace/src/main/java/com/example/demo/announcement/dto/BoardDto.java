package com.example.demo.announcement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BoardDto {
	
	private Integer announ_id;	// 유저 아이디 PK
	private String subject;
	private String content;
	private LocalDate create_date;
	
	
	public Integer getAnnoun_id() {
		return announ_id;
	}
	public void setAnnoun_id(Integer announ_id) {
		this.announ_id = announ_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDate create_date) {
		this.create_date = create_date;
	}

}
