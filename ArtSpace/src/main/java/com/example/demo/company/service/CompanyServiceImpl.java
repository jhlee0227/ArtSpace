package com.example.demo.company.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.company.dao.CompanyDAO;
import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.file.dao.FileDAO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.mypage.dao.MypageDAO;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO companyDAO;
	
	@Autowired
	MypageDAO mypageDAO;
	
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
			
			int reserve_id = reservationDTO.getReserve_id();
			reservationDTO.setReservationEquipmentList(mypageDAO.getAllReservationEquip(reserve_id));
			reservationDTO.setReserveDateList(mypageDAO.getAllReserveDate(reserve_id));
		} 
		return reserveList;
	}

	@Override
	public Map<Integer, LocalDate> getEarliestReserveDates(List<ReservationDTO> reservationList) {
	    Map<Integer, LocalDate> earliestDates = new HashMap<>();
	    for (ReservationDTO reservation : reservationList) {
	        List<ReserveDateDTO> reserveDateList = reservation.getReserveDateList();
	        if (reserveDateList != null && !reserveDateList.isEmpty()) {
	            LocalDate earliestDate = null;
	            for (ReserveDateDTO reserveDate : reserveDateList) {
	                LocalDate date = LocalDate.parse(reserveDate.getReserve_date());
	                if (earliestDate == null || date.isBefore(earliestDate)) {
	                    earliestDate = date;
	                }
	            }
	            earliestDates.put(reservation.getReserve_id(), earliestDate);
	        }
	    }
	    return earliestDates;
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
