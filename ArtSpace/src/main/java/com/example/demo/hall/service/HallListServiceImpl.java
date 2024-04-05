package com.example.demo.hall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hall.dao.HallDAO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallTimeDTO;

@Service
public class HallListServiceImpl implements HallListService{

	@Autowired
	HallDAO hallDAO;
	
	@Override
	public List<HallDTO> getList(String sort) {
		List<HallDTO> hallList = hallDAO.getHallList(sort);
		
		for (HallDTO hallDTO : hallList) {
			List<HallTimeDTO> time = hallDAO.getHallTimeList(hallDTO.getHall_id());
			hallDTO.setHallTimeList(time);
		}
		return hallList;
	}

	@Override
	public List<HallDTO> getFilterData(List<String> local) {
		
		String filter = "";
		for (int i = 0; i < local.size(); i++) {			
			filter += local.get(i);
			if(i+1 < local.size()) {
				filter += "|";
			}
		}
		
		List<HallDTO> hallList = hallDAO.getHallFilterList(filter);
		
		for (HallDTO hallDTO : hallList) {
			List<HallTimeDTO> time = hallDAO.getHallTimeList(hallDTO.getHall_id());
			hallDTO.setHallTimeList(time);
		}
		return hallList;
	}

	
}
