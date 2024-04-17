package com.example.demo.reservation.service;

import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;

public interface ReservationService {

	void insert(ReservationDTO reservation);

	int duplicationCheck(ReserveDateDTO date);

	int dayDuplicationCheck(ReserveDateDTO date);

}
