package com.example.demo.hall.dto;

public class HallTimeDTO {
	private Integer hall_id;	// 공연장ID
	private String type;		// 무슨 시간대인지 (오전, 오후, 저녁, 하루)
	private Integer price;		// 가격
	private boolean ischeck;	// 체크했는지(작성 폼용)
	
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public boolean getIscheck() {
		return ischeck;
	}
	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}

	
}
