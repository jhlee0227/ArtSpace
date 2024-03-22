package com.example.demo.hall.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EquipmentDTO {

	private Integer equipId;	// 장비ID	 PK
	private Integer hallId;		// 공연장ID FK
	private String equip_name;	// 장비명
	private String equip_type;	// 장비 타입
	private Integer equip_num;	// 장비 수량
	private Integer equip_price;	// 장비 가격
	
}
