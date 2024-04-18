package com.example.demo.reservation.service;

import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;
import com.example.demo.user.dto.UserDTO;

public interface ReservationService {

	void insert(ReservationDTO reservation);

	int duplicationCheck(ReserveDateDTO date);

	int dayDuplicationCheck(ReserveDateDTO date);

	UserDTO getSCUser(Integer hall_id);

	ReservationDTO getReservation(Integer reserve_id);

}
