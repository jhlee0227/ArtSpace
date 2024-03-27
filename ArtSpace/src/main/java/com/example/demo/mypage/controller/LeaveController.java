package com.example.demo.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.mypage.service.MypageService;
import com.example.demo.user.dto.UserDTO;

@Controller
public class LeaveController {

	@Autowired
	MypageService mypageService;
	
	// 탈퇴
	@PostMapping("/")
	public String leave(@ModelAttribute UserDTO dto) {
		
		mypageService.leave(dto);
		return "redirect:";
	}

}
