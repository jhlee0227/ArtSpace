package com.example.demo.announcement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.SessionUtil;
import com.example.demo.announcement.dto.AskDto;

import com.example.demo.announcement.service.AskService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/ask")
public class AskController {

	@Autowired
	private AskService askService;
	
	@Autowired
	HttpSession session;
	
	SessionUtil user_session = new SessionUtil();
	
	@GetMapping("")
	public String showboardPage1(Model model) {
		List<AskDto> askList = askService.getAsk();
		model.addAttribute("askList", askList);
		
	
	user_session.setSessionValue(session);
	model.addAttribute("user_id", user_session.getUser_id());
	model.addAttribute("nickname", user_session.getNickname());
	model.addAttribute("authority", user_session.getAuthority());
	return "html/announcement/ask";	

	}
	
	@GetMapping("/write")
	public String showWrite() {
		return "html/announcement/ask_notice";
	}
	
	
	@PostMapping("/insert")
	public String insertUser(@ModelAttribute AskDto askDTO) {
		askDTO.setCreate_date(LocalDate.now());
		askService.insert(askDTO);
		return "redirect:/ask";
	}


}
