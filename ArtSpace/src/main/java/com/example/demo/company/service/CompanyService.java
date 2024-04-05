package com.example.demo.company.service;

import java.util.List;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReservationDTO;

public interface CompanyService {

	public List<HallDTO> getHall(Integer user_id);

	public List<ReservationDTO> getReserve(Integer user_id);

	public void reserveDelete(Integer reserve_id);

}
