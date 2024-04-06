package com.example.demo.home;

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
public class HomeController{

	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HallListService hallListService;
	
	@GetMapping("/")
	public String showMain(Model model) {
		
		user_session.setSessionValue(session);
	
		List<HallDTO> hallList = hallListService.getList();
		model.addAttribute("hallList", hallList);
		
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		
		return "html/index";
	}
	

	
}
