package com.example.demo.hall.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.UUIDgeneration;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.service.HallService;


@Controller
@RequestMapping("hall")
public class HallController {

	@Autowired
	private HallService hallService;

	private Integer hall_id;
	
	// 기본 공연장 정보 입력 화면 띄우기
	@GetMapping("form")
	public String showForm(Model model) {
		model.addAttribute("hall_info", new HallDTO());
		model.addAttribute("action", "/hall/form/insert");
		return "html/hall_form";
	}

	// 이전 버튼을 눌렀을때 공연장 정보 수정 화면 띄우기
	@GetMapping("form/{id}")
	public String shwForm(Model model, @PathVariable("id") Integer id) {
		HallDTO hallInfo = hallService.findById(id);		
		model.addAttribute("hall_info", hallInfo);
		model.addAttribute("action", "/hall/form/update/" + id);
		
		return "html/hall_form";
	}
	

	// insert 처리
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

	// update 처리
	@PostMapping("/form/update/{id}")
	public String hallUpdate(@ModelAttribute HallDTO hallDTO, @PathVariable("id") Integer id) {	
		hallDTO.setCreate_date(LocalDate.now());
		hallDTO.setHall_id(id);
		hallService.update(hallDTO);
		
		return "redirect:/hall/form/equipment/" + id;
	}

	
	// 장비 화면 띄우기
	@GetMapping("form/equipment/{id}")
	public String showFormEquipment(@PathVariable("id") Integer id, Model model) {
		List<EquipmentDTO> equiList = hallService.getEquiList(id);
		model.addAttribute("equiList", equiList);
		model.addAttribute("hall_id", id);
		return "html/hall_form_equipment";
	}
	
	// 이전 누르면
	@PostMapping("form/equipment/insert/{id}")
	public String insertEquipment(@ModelAttribute Map<EquipmentDTO, String> equiDTOList, @PathVariable("id") Integer id) {
		
		System.out.println("111111111111111111111111111111111111111111111");
		//hallService.insertEqui(equiDTOList, id);
		
		return "redirect:form/"+id;
	}
	
}
