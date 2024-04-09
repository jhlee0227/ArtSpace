package com.example.demo.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.company.dao.CompanyDAO;
import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReservationDTO;
import com.example.demo.hall.dto.ReviewDTO;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO companyDAO;
	
	@Override
	public List<HallDTO> getHall(Integer user_id) {
		
		return companyDAO.getHall(user_id);
	}

	@Override
	public List<ReservationDTO> getReserve(Integer user_id) {
		
		return companyDAO.getReserve(user_id);
	}

	@Override
	public void reserveDelete(Integer reserve_id) {
		
		companyDAO.reserveDelete(reserve_id);
	}

	@Override
	public void updateInfo(CompanyDTO dto) {
		
		companyDAO.updateInfo(dto);
	}

	@Override
	public CompanyDTO findByID(Integer user_id) {
		
		return companyDAO.findByID(user_id);
	}

	@Override
	public void hallDelete(Integer hall_id) {
		companyDAO.hallDelete(hall_id);
	}

	@Override
	public int fileCount(Integer company_id) {
		
		return companyDAO.fileCount(company_id);
	}

}
