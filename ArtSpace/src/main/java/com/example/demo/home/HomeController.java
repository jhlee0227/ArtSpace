package com.example.demo.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.SessionUtil;

import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController{

	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/")
	public String showMain(Model model) {
		
		user_session.setSesstionValue(session);
		
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		
		return "html/index";
	}
	

	
}
