package com.example.demo.hall.dto;

public class ReserveDateDTO {
	private String reserve_date;	// 예약한 날짜
	private String reserve_time;	// 예약한 시간
	private Integer reserve_id; 	// 예약ID 예약 테이블 FK
	
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
	public String getReserve_time() {
		return reserve_time;
	}
	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}
	public Integer getReserve_id() {
		return reserve_id;
	}
	public void setReserve_id(Integer reserve_id) {
		this.reserve_id = reserve_id;
	}
}
