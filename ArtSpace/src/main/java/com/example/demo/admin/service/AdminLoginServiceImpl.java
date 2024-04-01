package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminLoginDAO;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{

	@Autowired
	AdminLoginDAO adminLoginDAO;
	
	@Override
	public int emailCheck(String email) {
		return adminLoginDAO.emailCheck(email);	}

}
