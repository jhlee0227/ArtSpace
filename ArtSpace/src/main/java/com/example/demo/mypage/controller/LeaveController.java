package com.example.demo.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.mypage.service.MypageService;
import com.example.demo.user.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class LeaveController {

	@Autowired
	MypageService mypageService;
	
	@Autowired
	HttpSession session;
	
	// 탈퇴
	@PostMapping("/leave")
	public String leave(@ModelAttribute UserDTO dto) {
		Integer userId = (Integer) session.getAttribute("user_id");
		dto.setUser_id(userId);
		mypageService.leave(dto);
		session.invalidate();
		return "redirect:/login";
	}

}
