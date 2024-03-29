package com.example.demo.hall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.service.HallListService;

@Controller
public class HallListController {
	
	@Autowired
	HallListService hallListService;

	@GetMapping("/list")
	public String showHallList(Model model) {
		List<HallDTO> hallList = hallListService.getList("create_date");
		model.addAttribute("hallList", hallList);
		return "html/hall/hall_list";	
	}
	
	
}
