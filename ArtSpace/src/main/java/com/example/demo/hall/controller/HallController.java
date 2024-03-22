package com.example.demo.hall.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.service.HallService;

//import com.example.demo.hall.dto.HallDTO;
//import com.example.demo.hall.service.HallService;


@Controller
@RequestMapping("hall")
public class HallController {

	@Autowired
	private HallService hallService;
	
	
	@GetMapping("form")
	public String showForm() {
		return "html/hall_form";
	}
	
	@PostMapping("/form/insert")
	public String hallCreate(@ModelAttribute HallDTO hallDTO) {		
		hallDTO.setCreate_date(LocalDate.now());
		hallService.insert(hallDTO);
		return "redirect:/";
	}
	
	
	@GetMapping("form/equipment")
	public String showFormEquipment() {
		return "html/hall_form_equipment";
	}
	
	
	
}
