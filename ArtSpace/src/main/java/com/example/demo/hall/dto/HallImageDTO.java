package com.example.demo.hall.dto;

import java.time.LocalDate;

public class HallImageDTO {
	// 공연장 사진 시간에 대한 table
	
	private Integer h_image_id;	// 공연장 이미지 ID
	private String hall_id;		// 공연장 ID
	private String file_id;		// 파일 ID
	
	public Integer getH_image_id() {
		return h_image_id;
	}
	public void setH_image_id(Integer h_image_id) {
		this.h_image_id = h_image_id;
	}
	public String getHall_id() {
		return hall_id;
	}
	public void setHall_id(String hall_id) {
		this.hall_id = hall_id;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
	
	
}
