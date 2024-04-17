package com.example.demo.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.reservation.dao.ReservationDAO;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReservationEquipmentDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDAO reservationDAO;
	
	// 예약 값 넣기
	@Override
	public void insert(ReservationDTO reservation) {
		reservationDAO.insert(reservation);
		
		for (ReserveDateDTO date : reservation.getReserveDateList()) {
			date.setReserve_id(reservation.getReserve_id());
			reservationDAO.insertTime(date);
		}
		
		for (ReservationEquipmentDTO equip : reservation.getReservationEquipmentList()) {
			equip.setReserve_id(reservation.getReserve_id());
			reservationDAO.insertEquip(equip);			
		}
		
	}

	// 동시간대 중복 체크
	@Override
	public int duplicationCheck(ReserveDateDTO date) {
		return reservationDAO.duplicationCheck(date);
	}

	// 하루일 경우 그날 중복되는 시간 체크
	@Override
	public int dayDuplicationCheck(ReserveDateDTO date) {
		return reservationDAO.dayDuplicationCheck(date);
	}
	

}
