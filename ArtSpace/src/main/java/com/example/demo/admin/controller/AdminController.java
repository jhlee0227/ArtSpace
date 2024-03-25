package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

	
	@GetMapping("")
	public String admin() {
		return "html/admin";
	}
	
	@GetMapping("/hallinfo")
	public String hallinfo() {
		return "html/hall_info";
	}
	
	@GetMapping("/notice")
	public String notice() {
		return "html/admin_notice";
	}
	
	@GetMapping("/review")
	public String review() {
		return "html/admin_review";
	}
	
}
