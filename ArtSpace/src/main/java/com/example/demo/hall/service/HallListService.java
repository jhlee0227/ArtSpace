package com.example.demo.hall.service;

import java.util.List;
import java.util.Map;

import com.example.demo.hall.dto.HallDTO;

public interface HallListService {

	List<HallDTO> getList(String sort);

	List<HallDTO> getFilterData(List<String> valueArr);


}
