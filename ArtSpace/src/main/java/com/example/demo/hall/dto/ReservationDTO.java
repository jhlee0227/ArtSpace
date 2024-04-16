package com.example.demo.hall.dto;

import java.util.List;

import com.example.demo.file.dto.FileDTO;

public class ReservationDTO {

	private Integer reserve_id; 		// 예약ID PK
	private String reserve_status; 		// 예약상태 (Y/N)
	private Integer estimate;			// 견적가
	private Integer user_id; 			// 유저ID FK
	private Integer hall_id; 			// 공연장ID FK

	// 홀 정보
	private String hall_name; 			// 공연장 이름
	private String address1; 			// 기본주소
	private String address2; 			// 상세주소

	// 장비 정보
	private String equip_name; 			// 장비 이름
	private String equip_price; 		// 장비 가격
	
	// 예약한 장비 정보
	private Integer equip_num; 			// 사용할 장비 갯수

	// 사용자가 예약한 날짜와 시간
	private List<ReserveDateDTO> ReserveDateList;
	
	// 예약자이름, 핸드폰번호
	private String name;
	private String phone;
	
	private FileDTO mainImage;

	public Integer getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(Integer reserve_id) {
		this.reserve_id = reserve_id;
	}

	public String getReserve_status() {
		return reserve_status;
	}

	public void setReserve_status(String reserve_status) {
		this.reserve_status = reserve_status;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
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

	public String getHall_name() {
		return hall_name;
	}

	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getEquip_name() {
		return equip_name;
	}

	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}

	public String getEquip_price() {
		return equip_price;
	}

	public void setEquip_price(String equip_price) {
		this.equip_price = equip_price;
	}

	public Integer getEquip_num() {
		return equip_num;
	}

	public void setEquip_num(Integer equip_num) {
		this.equip_num = equip_num;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public FileDTO getMainImage() {
		return mainImage;
	}

	public void setMainImage(FileDTO mainImage) {
		this.mainImage = mainImage;
	}


	public List<ReserveDateDTO> getReserveDateList() {
		return ReserveDateList;
	}

	public void setReserveDateList(List<ReserveDateDTO> reserveDateList) {
		ReserveDateList = reserveDateList;
	}
	
	
}
