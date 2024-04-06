package com.example.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.dao.CorporationDAO;
import com.example.demo.user.dto.CorporationDTO;
import com.example.demo.user.dto.UserDTO;

@Service
public class CorporationServiceImpl implements CorporationService{

	@Autowired
	CorporationDAO corporationDAO;
	
	@Override
	public void insertUser(UserDTO user) {
		user.setAuthority("SCN");
		corporationDAO.insertUser(user);
	}
	@Override
	public void insertCompanyNumber(CorporationDTO corpor) {
		corporationDAO.insertCompanyNumber(corpor);
	}
	@Override
	public void updateUser(UserDTO userDTO) {
		corporationDAO.updateUser(userDTO);
		
	}



}
