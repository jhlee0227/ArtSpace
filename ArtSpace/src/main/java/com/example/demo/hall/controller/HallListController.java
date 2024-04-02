package com.example.demo.hall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.SessionUtil;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.service.HallListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HallListController {

	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HallListService hallListService;

	@GetMapping("/list")
	public String showHallList(Model model) {
		List<HallDTO> hallList = hallListService.getList("create_date");
		model.addAttribute("hallList", hallList);
		
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		return "html/hall/hall_list";	
	}
	
	
}
