package com.example.demo.hall.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallFilterDTO;

public interface HallListService {

	List<HallDTO> getList(Integer integer);

	List<HallDTO> getFilterData(HallFilterDTO filter, Integer user_id);


}
