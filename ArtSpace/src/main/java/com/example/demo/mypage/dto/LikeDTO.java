package com.example.demo.mypage.dto;

public class LikeDTO {

	private Integer like_id;	// 즐겨찾기ID PK
	private String status;		// 즐겨찾기 상태 Y/N으로 처리
	private Integer user_id;	// 유저ID FK
	private Integer hall_id;	// 공연장ID FK
	
	public Integer getLike_id() {
		return like_id;
	}
	public void setLike_id(Integer like_id) {
		this.like_id = like_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	
	
}
