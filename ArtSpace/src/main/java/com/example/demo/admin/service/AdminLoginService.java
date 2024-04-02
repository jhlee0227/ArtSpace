package com.example.demo.admin.service;

import com.example.demo.admin.dto.AdminDTO;

public interface AdminLoginService {

	int emailCheck(String email);

	void insert(AdminDTO adminDTO);

	AdminDTO login(AdminDTO adminDTO);

}
