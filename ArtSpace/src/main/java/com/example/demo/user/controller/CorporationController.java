package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.service.CorporationService;

@Controller
@RequestMapping("cor")
public class CorporationController {

	@Autowired
	CorporationService corporationService;
	
	// 법인 회원가입인증 화면 띄움
	@GetMapping("corporation")
	public String checkCor() {
		return "html/login/login_corporation";
	} 
	
	// 법인 데이터 회원가입 화면 띄움
	@PostMapping("join")
	public String joinCor() {
		return "html/login/login_joincorporation";
	} 
	
	// 법인 회원가입인증코드 화면 띄움
	@PostMapping("joinCode")
	public String codeCor() {
		return "html/login/login_code_corporation";
	} 
	
	// 법인 회원가입 완료 화면 띄움
	@PostMapping("welcome")
	public String welcomeCor() {
		return "html/login/login_welcome_corporation";
	} 
	
}
