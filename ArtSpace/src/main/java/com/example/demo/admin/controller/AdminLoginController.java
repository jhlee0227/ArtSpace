package com.example.demo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.admin.service.AdminLoginService;

@Controller
@RequestMapping("yjh-console")
public class AdminLoginController {

	@Autowired
	AdminLoginService adminLoginService;
	
	@GetMapping("")
	public String login() {
		return "html/login/admin_login";
	}
	
	@GetMapping("join")
	public String joinform() {
		return "html/login/admin_join";
	}
	
	@RequestMapping(value="/emailCheck" ,method = RequestMethod.POST)
	@ResponseBody
	public String idcheck(@RequestParam(value="email")  String email) {
		String chk = "";
		int result = 0;
		
		result = adminLoginService.emailCheck(email);
		
		if(result > 0) {
			chk = "redundancy"; 	// 아이디 중복
		} else if(result == 0){
			chk = "noredundancy";	// 아이디 중복 아님
		}
		return chk;
	}
	
	
}
