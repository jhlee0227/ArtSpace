package com.example.demo.announcement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("anno")
public class AnnouncementController {

	@GetMapping("")
	public String showAnnouncementPage() {
		return "html/mypage/announcement";
		
	}
	
}
