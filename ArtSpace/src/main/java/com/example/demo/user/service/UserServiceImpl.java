package com.example.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.dao.UserDAO;
import com.example.demo.user.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public void insert(UserDTO userDTO) {
		userDTO.setAuthority("SU");		// 권한 설정
		userDTO.setLeave_status('N');	// 탈퇴여부 설정
		userDAO.insert(userDTO);		
	}

	// 로그인 처리
	@Override
	public UserDTO login(UserDTO userDTO) {
		return userDAO.login(userDTO);
	}


}
