package com.example.demo.hall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hall.dao.DetailDAO;
import com.example.demo.hall.dto.HallDTO;

@Service
public class DetailServiceImpl implements DetailService {
	
	@Autowired
	DetailDAO detailDAO;

	@Override
	public HallDTO findByID(Integer id) {
		
		return detailDAO.findById(id);
	}

}
