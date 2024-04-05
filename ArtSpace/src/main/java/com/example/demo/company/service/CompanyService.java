package com.example.demo.company.service;

import java.util.List;

import com.example.demo.hall.dto.HallDTO;

public interface CompanyService {

	public List<HallDTO> getHall(Integer user_id);

}
