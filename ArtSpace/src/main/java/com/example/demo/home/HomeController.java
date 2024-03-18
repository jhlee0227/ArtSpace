package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping("/")
	public String ShowMain() {
		return "html/index.html";
	}
	
	@GetMapping("/list")
	public String ShowHallList() {
		return "html/hall_list";
	}
	
	
}
