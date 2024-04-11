package com.example.demo.hall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.file.dao.FileDAO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dao.HallDAO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallFilterDTO;
import com.example.demo.hall.dto.HallTimeDTO;

@Service
public class HallListServiceImpl implements HallListService{

	@Autowired
	HallDAO hallDAO;
	@Autowired
	FileDAO fileDAO;
	
	@Override
	public List<HallDTO> getList() {
		List<HallDTO> hallList = hallDAO.getHallList();
		
		for (HallDTO hallDTO : hallList) {
			List<HallTimeDTO> time = hallDAO.getHallTimeList(hallDTO.getHall_id());
			FileDTO mainImage = fileDAO.getHallMainFile(hallDTO.getHall_id());

			hallDTO.setHallTimeList(time);
			hallDTO.setMainImage(mainImage);
		}
		return hallList;
	}

	@Override
	public List<HallDTO> getFilterData(HallFilterDTO filter) {
		
		String local = "";
		if(filter.getLocalList() != null) {
			for (int i = 0; i < filter.getLocalList().size(); i++) {			
				local += filter.getLocalList().get(i);
				
				if(i+1 < filter.getLocalList().size()) {
					local += "|";
				}
			}	
		}
		
		
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("local", local);
		filterMap.put("minPrice", filter.getMinPrice());
		filterMap.put("maxPrice", filter.getMaxPrice());
		filterMap.put("maxPeople", filter.getMaxPeople());		
		filterMap.put("sort", filter.getSort());		
		
		List<HallDTO> hallList = hallDAO.getHallFilterList(filterMap);
		
		for (HallDTO hallDTO : hallList) {
			List<HallTimeDTO> time = hallDAO.getHallTimeList(hallDTO.getHall_id());
			FileDTO mainImage = fileDAO.getHallMainFile(hallDTO.getHall_id());
			
			hallDTO.setHallTimeList(time);
			hallDTO.setMainImage(mainImage);
		}
		return hallList;
	}

	
}
