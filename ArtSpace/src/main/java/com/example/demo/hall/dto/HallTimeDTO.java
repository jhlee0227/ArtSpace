package com.example.demo.hall.dto;

public class HallTimeDTO {
	// 예약 가능 시간 오전 오후 저녁 종일
	private Integer hall_id;
	
	private String type;
	private Integer m_price;

	
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getM_price() {
		return m_price;
	}
	public void setM_price(Integer m_price) {
		this.m_price = m_price;
	}

	
	
	
}
