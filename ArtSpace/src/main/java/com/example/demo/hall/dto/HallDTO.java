package com.example.demo.hall.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class HallDTO {

	private Integer hall_id;		// 공연장ID PK
	private String hall_name;	// 공연장명
	private String address1;		// 주소
	private String address2;		// 상세주소
	private String hall_description;	// 공연장설명
	private String warning;			// 주의 사항
	private Integer area;		// 면적
	private Integer width;		// 가로
	private Integer length;		// 세로
	private Integer height;		// 높이
	private Integer maximum;	// 최대수용인원수
	private Integer views;		// 조회수
	private LocalDate create_date;		// 등록 날짜
	private Integer user_id;		// 유저ID FK

	
}
