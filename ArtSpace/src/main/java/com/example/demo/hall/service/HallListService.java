package com.example.demo.hall.service;

import java.util.List;

import com.example.demo.hall.dto.HallDTO;

public interface HallListService {

	List<HallDTO> getList(String sort);


}
