package com.example.demo.reservation.dto;

public class ReservationEquipmentDTO {
	
	private Integer re_equipment_id;		// 예약장비 인덱스 PK
	private String equip_type;		// 장비 타입
	private String equip_name; 		// 장비 이름
	private Integer equip_num; 			// 사용할 장비 갯수
	private String equip_price; 	// 장비 가격
	private Integer reserve_id;		// 예약ID 예약 테이블 FK
	
	
	public Integer getRe_id() {
		return re_equipment_id;
	}
	public void setRe_id(Integer re_equipment_id) {
		this.re_equipment_id = re_equipment_id;
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
	public String getEquip_price() {
		return equip_price;
	}
	public void setEquip_price(String equip_price) {
		this.equip_price = equip_price;
	}
	public Integer getReserve_id() {
		return reserve_id;
	}
	public void setReserve_id(Integer reserve_id) {
		this.reserve_id = reserve_id;
	}
	
	
	
}
