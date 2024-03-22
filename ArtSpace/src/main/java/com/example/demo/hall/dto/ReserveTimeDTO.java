package com.example.demo.hall.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReserveTimeDTO {
	// 공연장 예약가능 시간에 대한 table
	
	private Integer morning;	// 오전
	private Integer after;		// 오후
	private Integer eve;		// 저녁
	private Integer full;		// 종일
	private Integer hallId;		// 공연장 ID FK
	
}
