package com.example.demo.hall.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.UUIDgeneration;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.service.HallService;

import jakarta.validation.Valid;


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
	

	// 이전 누르면 장비 저장하고 이전 화면으로 연결
	@PostMapping("form/equipment/insert/{id}")
	public String insertEquipment(@Valid @ModelAttribute HallDTO hallDTO, @PathVariable("id") Integer id) {
		hallService.deleteEqui(id);	// 전부 삭제하고
		List<EquipmentDTO> equiList = hallDTO.getEquiList();

		// 값이 모두 채워진 것만 리스트에 추가하기.
		for (int i = 0; i < hallDTO.getEquiList().size(); i++) {
			if(equiList.get(i).getEquip_name() != null && !equiList.get(i).getEquip_type().equals("none") &&
							equiList.get(i).getEquip_num() != null && equiList.get(i).getEquip_price() != null) {
				hallService.insertEqui(equiList.get(i), id);
			}
		}
		return "redirect:/hall/form/"+id;
	}
	
	// 등록 누르면 장비 저장하고 my페이지로 연결
	@PostMapping("form/complete/{id}")
	public String completeInsertHall(@Valid @ModelAttribute HallDTO hallDTO, @PathVariable("id") Integer id) {
		hallService.deleteEqui(id);	// 전부 삭제하고
		List<EquipmentDTO> equiList = hallDTO.getEquiList();

		// 값이 모두 채워진 것만 리스트에 추가하기.
		for (int i = 0; i < hallDTO.getEquiList().size(); i++) {
			if(equiList.get(i).getEquip_name() != null && !equiList.get(i).getEquip_type().equals("none") &&
							equiList.get(i).getEquip_num() != null && equiList.get(i).getEquip_price() != null) {
				hallService.insertEqui(equiList.get(i), id);
			}
		}
		
		return "redirect:/hall/detail_page/"+id;
	}
	
	@PostMapping("form/cancel/{id}")
	public String cancelHall(@PathVariable("id") Integer id) {
		hallService.cancelHall(id);
		return "redirect:/";
	}

	
	// 임시 
	@GetMapping("detail_page/{id}")
	public String detailPage(Model model, HallDTO hallDTO, EquipmentDTO equipDTO, @PathVariable("id") Integer id) {
		
		return "html/detail_page";
	}
	
	
	
}
