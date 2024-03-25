package com.example.demo.hall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.UUIDgeneration;
import com.example.demo.hall.dao.HallDAO;
import com.example.demo.hall.dto.HallDTO;


@Service
public class HallServiceImpl implements HallService{

	@Autowired
	HallDAO hallDAO;
	
	@Override
	public void insert(HallDTO hallDTO) {
		hallDAO.insert(hallDTO);
	}

	@Override
	public HallDTO findById(Integer id) {
		
		return hallDAO.findById(id);
	}

	@Override
	public Integer findLastIndex() {
		return hallDAO.findLastIndex();
	}

	@Override
	public void update(HallDTO hallDTO) {
		hallDAO.update(hallDTO);
		
	}
	
}
