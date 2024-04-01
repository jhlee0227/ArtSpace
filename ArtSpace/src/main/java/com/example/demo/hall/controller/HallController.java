package com.example.demo.hall.controller;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

import com.example.demo.SessionUtil;
import com.example.demo.UUIDgeneration;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallTimeDTO;
import com.example.demo.hall.service.HallService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("hall")
public class HallController {

	@Autowired
	private HallService hallService;
	
	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	HttpSession session;
	
	// 기본 공연장 정보 입력 화면 띄우기
	@GetMapping("form")
	public String showForm(Model model) {
		user_session.setSesstionValue(session);

		// 로그인 권한 체크
		if(user_session.getUser_id() == null) {
			return "redirect:/login";
		} else if (!user_session.getAuthority().equals("SC")){
			//법인 이용자인지 권한 체크
			//return "redirect:/";
		} else {
			model.addAttribute("user_id", user_session.getUser_id());
			model.addAttribute("nickname", user_session.getNickname());
		}
				
		// 초기화
		HallDTO hall = hallService.newHallform();

		model.addAttribute("hall_info", hall);		
		model.addAttribute("action", "/hall/form/insert");
		return "html/hall/hall_form";
	}

	// 이전 버튼을 눌렀을때 공연장 정보 수정 화면 띄우기
	@GetMapping("form/{id}")
	public String shwForm(Model model, @PathVariable("id") Integer id) {
		user_session.setSesstionValue(session);
		
		if(user_session.getUser_id() == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("user_id", user_session.getUser_id());
			model.addAttribute("nickname", user_session.getNickname());
		}

		HallDTO hallInfo = hallService.findById(id);
		hallInfo.setHallTimeList(hallService.setHallTimeList(hallInfo));

		model.addAttribute("hall_info", hallInfo);
		model.addAttribute("action", "/hall/form/update/" + id);

		return "html/hall/hall_form";
	}
	
	
	// insert 처리
	@PostMapping("/form/insert")
	public String hallCreate(@ModelAttribute HallDTO hallDTO) {				
		user_session.setSesstionValue(session);
		
		if(user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		hallDTO.setCreate_date(LocalDate.now());
		hallDTO.setUser_id(user_session.getUser_id());

		hallService.insert(hallDTO);
		return "redirect:/hall/form/equipment/" + hallDTO.getHall_id();
	}
	

	// update 처리
	@PostMapping("/form/update/{id}")
	public String hallUpdate(@ModelAttribute HallDTO hallDTO, @PathVariable("id") Integer id) {	
		user_session.setSesstionValue(session);	
		if(user_session.getUser_id() == null) {
			return "redirect:/login";
		}

		hallDTO.setCreate_date(LocalDate.now());
		hallDTO.setHall_id(id);
		hallDTO.setUser_id(user_session.getUser_id());
		hallService.update(hallDTO);

		return "redirect:/hall/form/equipment/" + id;
	}
	
	
	// 장비 화면 띄우기
	@GetMapping("form/equipment/{id}")
	public String showFormEquipment(@PathVariable("id") Integer id, Model model) {
		user_session.setSesstionValue(session);

		if(user_session.getUser_id() == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("user_id", user_session.getUser_id());
			model.addAttribute("nickname", user_session.getNickname());
		}

		List<EquipmentDTO> equiList = hallService.getEquiList(id);
		model.addAttribute("equiList", equiList);
		
		model.addAttribute("hall_id", id);
		return "html/hall/hall_form_equipment";
	}
	
	// 이전 누르면 장비 저장하고 이전 화면으로 연결
	@PostMapping("form/equipment/insert/{id}")
	public String insertEquipment(@Valid @ModelAttribute HallDTO hallDTO, @PathVariable("id") Integer id) {
		user_session.setSesstionValue(session);		
		if(user_session.getUser_id() == null) {
			return "redirect:/login";
		}

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
		user_session.setSesstionValue(session);		
		if(user_session.getUser_id() == null) {
			return "redirect:/login";
		}

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
		user_session.setSesstionValue(session);		
		if(user_session.getUser_id() == null) {
			return "redirect:/login";
		}

		hallService.cancelHall(id);
		return "redirect:/";
	}

	
	// 임시 
	@GetMapping("detail_page/{id}")
	public String detailPage(Model model, HallDTO hallDTO, EquipmentDTO equipDTO, @PathVariable("id") Integer id) {
		user_session.setSesstionValue(session);

		if(user_session.getUser_id() != null) {
			model.addAttribute("user_id", user_session.getUser_id());
			model.addAttribute("nickname", user_session.getNickname());
		}
		
		return "html/hall/detail_page";
	}
	
	
	
}
