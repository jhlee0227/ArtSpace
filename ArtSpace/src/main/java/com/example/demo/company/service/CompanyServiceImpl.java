package com.example.demo.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.company.dao.CompanyDAO;
import com.example.demo.hall.dto.HallDTO;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO companyDAO;
	
	@Override
	public List<HallDTO> getHall(Integer user_id) {
		
		return companyDAO.getHall(user_id);
	}

}
