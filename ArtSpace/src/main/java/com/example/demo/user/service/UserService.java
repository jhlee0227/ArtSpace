package com.example.demo.user.service;

import com.example.demo.user.dto.UserDTO;

public interface UserService {

	public void insert(UserDTO userDTO);

	public UserDTO login(UserDTO userDTO);

	public int emailCheck(String email);

	public int phoneCheck(String phone);

}
