package com.example.demo.hall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.SessionUtil;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.service.DetailService;
import com.example.demo.hall.service.HallService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("hall")
public class DetailController {
	
	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	DetailService detailService;
	
	@Autowired
	HallService hallService;
	
	@Autowired
	HttpSession session;
	
	// 임시
	@GetMapping("detail/{id}")
	public String detailPage(Model model, @PathVariable("id") Integer id) {
		user_session.setSessionValue(session);

		if (user_session.getUser_id() != null) {
			model.addAttribute("user_id", user_session.getUser_id());
			model.addAttribute("nickname", user_session.getNickname());
		}
		
		// 공연장 기본 정보들 불러오기
		HallDTO hall = hallService.findById(id);
		hall.setHallTimeList(hallService.setHallTimeList(hall));
		hall.setEquiList(hallService.getEquiList(id));
		
		List<FileDTO> images = hallService.getImageList(id);
		model.addAttribute("images", images);
		model.addAttribute("hall", hall);		

		return "html/hall/detail_page";
	}
	

}
