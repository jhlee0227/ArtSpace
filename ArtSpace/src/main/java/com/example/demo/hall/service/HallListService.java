package com.example.demo.hall.service;

import java.util.List;
import java.util.Map;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallFilterDTO;

public interface HallListService {

	List<HallDTO> getList();

	List<HallDTO> getFilterData(HallFilterDTO filter);


}
