package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminDAO;
import com.example.demo.user.dto.UserDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public List<UserDTO> getAllUsers() {
		
		return adminDAO.getAllUsers();
	}

	@Override
	public void leave(Integer user_id) {
		adminDAO.leave(user_id);
		
	}

}
