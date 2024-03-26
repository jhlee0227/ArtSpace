package com.example.demo.hall.dto;

public class EquipmentDTO {

	private Integer equip_id;	// 장비ID	 PK
	private Integer hall_id;		// 공연장ID FK
	private String equip_type;	// 장비명
	private String equip_name;	// 장비 타입
	private Integer equip_num;	// 장비 수량
	private Integer equip_price;	// 장비 가격
	
	public Integer getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(Integer equip_id) {
		this.equip_id = equip_id;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public String getEquip_type() {
		return equip_type;
	}
	public void setEquip_type(String equip_type) {
		this.equip_type = equip_type;
	}
	public String getEquip_name() {
		return equip_name;
	}
	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
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
