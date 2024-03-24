package com.example.demo.hall.service;

import com.example.demo.hall.dto.HallDTO;

public interface HallService {

	public void insert(HallDTO hallDTO);

	public HallDTO findById(Integer id);


}
