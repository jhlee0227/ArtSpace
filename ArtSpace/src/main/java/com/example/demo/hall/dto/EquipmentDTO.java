package com.example.demo.hall.dto;

import java.time.LocalDate;

import lombok.Data;


public class EquipmentDTO {

	private Integer equipId;	// 장비ID	 PK
	private Integer hallId;		// 공연장ID FK
	private String equip_name;	// 장비명
	private String equip_type;	// 장비 타입
	private Integer equip_num;	// 장비 수량
	private Integer equip_price;	// 장비 가격
	
	public Integer getEquipId() {
		return equipId;
	}
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	public Integer getHallId() {
		return hallId;
	}
	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}
	public String getEquip_name() {
		return equip_name;
	}
	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}
	public String getEquip_type() {
		return equip_type;
	}
	public void setEquip_type(String equip_type) {
		this.equip_type = equip_type;
	}
	public Integer getEquip_num() {
		return equip_num;
	}
	public void setEquip_num(Integer equip_num) {
		this.equip_num = equip_num;
	}
	public Integer getEquip_price() {
		return equip_price;
	}
	public void setEquip_price(Integer equip_price) {
		this.equip_price = equip_price;
	}
	
	
}
