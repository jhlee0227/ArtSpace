package com.example.demo.hall.dto;

import java.time.LocalDate;

public class ReserveTimeDTO {
	// 공연장 예약가능 시간에 대한 table
	
	private Integer morning;	// 오전
	private Integer after;		// 오후
	private Integer eve;		// 저녁
	private Integer full;		// 종일
	private Integer hallId;		// 공연장 ID FK
	
	public Integer getMorning() {
		return morning;
	}
	public void setMorning(Integer morning) {
		this.morning = morning;
	}
	public Integer getAfter() {
		return after;
	}
	public void setAfter(Integer after) {
		this.after = after;
	}
	public Integer getEve() {
		return eve;
	}
	public void setEve(Integer eve) {
		this.eve = eve;
	}
	public Integer getFull() {
		return full;
	}
	public void setFull(Integer full) {
		this.full = full;
	}
	public Integer getHallId() {
		return hallId;
	}
	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}
	
	
}
