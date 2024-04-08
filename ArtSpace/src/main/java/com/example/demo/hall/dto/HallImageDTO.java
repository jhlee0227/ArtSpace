package com.example.demo.hall.dto;

public class HallImageDTO {
	// 공연장 사진 시간에 대한 table
	
	private Integer h_image_id;	// 공연장 이미지 ID
	private Integer hall_id;		// 공연장 ID
	private Integer file_id;		// 파일 ID
	
	public Integer getH_image_id() {
		return h_image_id;
	}
	public void setH_image_id(Integer h_image_id) {
		this.h_image_id = h_image_id;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id2) {
		this.file_id = file_id2;
	}
	
	
	
}
