package com.example.demo.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController{

	@Autowired
	HttpSession session;
	
	@GetMapping("/")
	public String showMain(Model model) {
		Integer user_id = (Integer) session.getAttribute("user_id");
		model.addAttribute("user_id", user_id);
		return "html/index";
	}
	

	
}
