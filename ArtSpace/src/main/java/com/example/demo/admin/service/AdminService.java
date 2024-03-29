package com.example.demo.admin.service;

import java.util.List;

import com.example.demo.user.dto.UserDTO;

public interface AdminService {

	public List<UserDTO> getAllUsers();

	public void leave(Integer user_id);

}
