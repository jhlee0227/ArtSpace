package com.example.demo.announcement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

@Controller
@RequestMapping("board")
public class BoardController {

	
	@Autowired
	private UserBoard userBoard;
	
	@GetMapping("/announcement/board")
	public String showboardPage() {
		return "html/announcement/board";
		
	}
	
	// 유저 데이터 insert 처리
	@PostMapping("/user/insert")
	public String insertUser(@ModelAttribute UserDTO userDTO) {
		
		userboard.insert(userDTO);
		return "html/login/logincode";
	}
}
