package com.example.demo.hall.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class HallImageDTO {
	// 공연장 사진 시간에 대한 table
	
	private Integer h_image_id;	// 공연장 이미지 ID
	private String hall_id;		// 공연장 ID
	private String file_id;		// 파일 ID
	
}
