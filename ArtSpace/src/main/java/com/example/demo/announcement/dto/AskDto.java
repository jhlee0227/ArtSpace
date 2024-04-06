package com.example.demo.announcement.dto;

import java.time.LocalDate;

public class AskDto {
	
	private Integer inquiry_id;	//
	private String subject;
	private String content;
	private LocalDate create_date;
	public Integer getInquiry_id() {
		return inquiry_id;
	}
	public void setInquiry_id(Integer inquiry_id) {
		this.inquiry_id = inquiry_id;
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
