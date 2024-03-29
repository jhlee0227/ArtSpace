package com.example.demo.hall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hall.dao.HallDAO;
import com.example.demo.hall.dto.HallDTO;

@Service
public class HallListServiceImpl implements HallListService{

	@Autowired
	HallDAO hallDAO;
	
	@Override
	public List<HallDTO> getList(String sort) {
		List<HallDTO> hallList = hallDAO.getHallList(sort);
		
		for (HallDTO hallDTO : hallList) {
			hallDTO.setHallTime(hallDAO.findHallTime(hallDTO.getHall_id()));
		}	
		return hallList;
	}
	

}
