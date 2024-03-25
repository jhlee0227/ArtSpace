package com.example.demo.hall.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.UUIDgeneration;
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
	public String showForm(Model model) {
		model.addAttribute("hall_info", new HallDTO());
		return "html/hall_form";
	}
	
	@GetMapping("form/{id}")
	public String shwForm(Model model, @PathVariable("id") Integer id) {
		HallDTO hallInfo = hallService.findById(id);		
		model.addAttribute("hall_info", hallInfo);
		return "html/hall_form";
	}
	
	
	private Integer hall_id;
	@PostMapping("/form/insert")
	public String hallCreate(@ModelAttribute HallDTO hallDTO) {		
		hall_id = hallService.findLastIndex();
		if(hall_id == null) hall_id = 0;
		hall_id++;

		hallDTO.setCreate_date(LocalDate.now());
		hallDTO.setHall_id(hall_id);

		hallService.insert(hallDTO);
		return "redirect:/hall/form/equipment/" + hallDTO.getHall_id();
	}
	
	@PostMapping("/form/update/{id}")
	public String hallUpdate(@ModelAttribute HallDTO hallDTO, @PathVariable("id") Integer id) {		
		hallService.update(hallDTO);

		return "redirect:/hall/form/equipment/" + hallDTO.getHall_id();
	}

	
	
	
	
	@GetMapping("form/equipment/{id}")
	public String showFormEquipment(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		return "html/hall_form_equipment";
	}
	
	
	
}
