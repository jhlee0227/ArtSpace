package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.admin.dao.AdminLoginDAO;
import com.example.demo.admin.dto.AdminDTO;
import com.example.demo.user.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{

	@Autowired
	AdminLoginDAO adminLoginDAO;
	
	@Override
	public int emailCheck(String email) {
		return adminLoginDAO.emailCheck(email);	
	}

	@Override
	public void insert(AdminDTO adminDTO) {
		adminDTO.setAuthority("SA");
		adminDTO.setNickname("공간");
		adminLoginDAO.insert(adminDTO);
	}

	@Override
	public AdminDTO login(AdminDTO adminDTO) {
		return adminLoginDAO.login(adminDTO);
	}


}
