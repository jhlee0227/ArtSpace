package com.example.demo.hall.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotEmpty;

public class HallDTO {

	private Integer hall_id; // 공연장ID PK
	private String hall_name; // 공연장명
	private Integer zip_code; // 우편번호
	private String address1; // 주소
	private String address2; // 상세주소
	private String hall_description; // 공연장설명
	private String warning; // 주의 사항
	private Integer area; // 면적
	private Integer width; // 가로
	private Integer length; // 세로
	private Integer height; // 높이
	private Integer maximum; // 최대수용인원수
	private Integer views; // 조회수
	private LocalDate create_date; // 등록 날짜
	private Integer user_id; // 유저ID FK
	private String name; // 유저이름

	private List<EquipmentDTO> equiList;
	private List<HallTimeDTO> hallTimeList;

	// 내가가진 시간별 가격대의 최솟값
	private Integer minPrice;

	// 예약 날짜와 시간 시작, 종료
	private String start_date;
	private String end_date;
	private String start_time;
	private String end_time;
	private Integer reserve_id;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EquipmentDTO> getEquiList() {
		return equiList;
	}

	public void setEquiList(List<EquipmentDTO> equiList) {
		this.equiList = equiList;
	}

	public List<HallTimeDTO> getHallTimeList() {
		return hallTimeList;
	}

	public void setHallTimeList(List<HallTimeDTO> hallTimeList) {
		this.hallTimeList = hallTimeList;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(Integer reserve_id) {
		this.reserve_id = reserve_id;
	}

}
