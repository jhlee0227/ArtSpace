package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping("/")
	public String showMain() {
		return "html/index";
	}
	
	@GetMapping("/list")
	public String showHallList() {
		return "html/hall_list";
	}
	
	
}
