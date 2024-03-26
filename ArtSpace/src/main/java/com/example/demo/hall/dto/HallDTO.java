package com.example.demo.hall.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;


public class HallDTO {

	private Integer hall_id;		// 공연장ID PK
	private String hall_name;		// 공연장명
	private Integer zip_code;
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
	
	private List<EquipmentDTO> equiList;

	// 예약 가능 시간 오전 오후 저녁 종일
	private Integer morning;
	private Integer after;
	private Integer eve;
	private Integer full;
	
	
	
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
	public Integer getZip_code() {
		return zip_code;
	}
	public void setZip_code(Integer zip_code) {
		this.zip_code = zip_code;
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
	public String getHall_description() {
		return hall_description;
	}
	public void setHall_description(String hall_description) {
		this.hall_description = hall_description;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getMaximum() {
		return maximum;
	}
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public LocalDate getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDate create_date) {
		this.create_date = create_date;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public List<EquipmentDTO> getEquiList() {
		return equiList;
	}
	public void setEquiList(List<EquipmentDTO> equiList) {
		this.equiList = equiList;
	}
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

	

	
	
}
