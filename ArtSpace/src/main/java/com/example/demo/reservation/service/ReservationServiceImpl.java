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

	@Override
	public int duplicationCheck(ReservationDTO reservation) {
		return reservationDAO.duplicationCheck(reservation);
		
	}
	

}
