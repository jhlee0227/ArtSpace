package com.example.demo.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mypage")
public class MypageController {

	@GetMapping("")
	public String mypage() {
		return "html/mypage";
	}
	
	@GetMapping("/performer")
	public String performer() {
		return "html/performer_info";
	}
	
	@GetMapping("/favorite")
	public String favorite() {
		return "html/my_favorites";
	}
	
	@GetMapping("/reserve")
	public String reserve() {
		return "html/reservation_list";
	}
	
	@GetMapping("/uselist")
	public String uselist() {
		return "html/use_list";
	}
	
	@GetMapping("/review")
	public String review() {
		return "html/my_review";
	}
}
