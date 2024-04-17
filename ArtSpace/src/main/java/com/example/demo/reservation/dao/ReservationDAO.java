package com.example.demo.reservation.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReservationEquipmentDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;

@Repository
public class ReservationDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	// 예약 저장
	public void insert(ReservationDTO reservation) {
		sqlSession.insert("reservation.insert", reservation);		
	}
	
	// 예약 시간 저장
	public void insertTime(ReserveDateDTO date) {
		sqlSession.insert("reservation.insertTime", date);				
	}
	
	// 예약 장비 저장
	public void insertEquip(ReservationEquipmentDTO equip) {
		sqlSession.insert("reservation.insertEquip", equip);				
	}

}
