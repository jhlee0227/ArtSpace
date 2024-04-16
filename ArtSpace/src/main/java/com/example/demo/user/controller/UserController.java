package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.EmailUtil;
import com.example.demo.email.EmailMessage;
import com.example.demo.email.EmailResponseDto;
import com.example.demo.email.EmailService;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailUtil emailUtil;
	
	// 일반 회원가입 화면 띄움
	@GetMapping("join")
	public String showJoin() {
		return "html/login/join_user_info";
	}
	

	// 유저 데이터 insert 처리 후 메일 인증 코드 부분으로 보냄
	@PostMapping("insert")
	public String insertUser(@ModelAttribute UserDTO userDTO) {
		userService.insert(userDTO);
		
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setTo(userDTO.getEmail());
		emailUtil.sendJoinMail(emailMessage);
		return "html/login/join_user_code";
	}
	
	
	// 회원가입 완료 페이지 오픈
	@PostMapping("welcome")
	public String welcome(@RequestParam("code") String code) {
		
		return "html/login/join_user_welcome";
	}

	
	

}
