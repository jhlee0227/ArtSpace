package com.example.demo.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.company.dao.CompanyDAO;
import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.file.dao.FileDAO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.reservation.dto.ReservationDTO;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO companyDAO;
	
	@Autowired
	FileDAO fileDAO;
	
	@Override
	public List<HallDTO> getHall(Integer user_id) {
		List<HallDTO> myHall = companyDAO.getHall(user_id);
		
		for (HallDTO hallDTO : myHall) {
			FileDTO mainImage = fileDAO.getHallMainFile(hallDTO.getHall_id());
			
			hallDTO.setMainImage(mainImage);
		}
		return myHall;
	}

	@Override
	public List<ReservationDTO> getReserve(Integer user_id) {
		List<ReservationDTO> reserveList = companyDAO.getReserve(user_id);
		
		for (ReservationDTO reservationDTO : reserveList) {
			FileDTO mainImage = fileDAO.getHallMainFile(reservationDTO.getHall_id());
			
			reservationDTO.setMainImage(mainImage);
		}
		return reserveList;
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
