package com.example.demo.user.service;

import com.example.demo.user.dto.CorporationDTO;
import com.example.demo.user.dto.UserDTO;

public interface CorporationService {

	void insertCompanyNumber(CorporationDTO corpor);

	void insertUser(UserDTO user);

	void updateUser(UserDTO userDTO);

}
