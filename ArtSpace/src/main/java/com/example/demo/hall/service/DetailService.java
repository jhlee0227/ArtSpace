package com.example.demo.hall.service;

import com.example.demo.hall.dto.HallDTO;

public interface DetailService {
	
	public HallDTO findById(Integer id, Integer user_id);

}
