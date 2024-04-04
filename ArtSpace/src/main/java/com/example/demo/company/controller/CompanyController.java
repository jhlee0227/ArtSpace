package com.example.demo.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.SessionUtil;
import com.example.demo.mypage.service.MypageService;
import com.example.demo.user.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyController {

	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	MypageService mypageService;
	
	@Autowired
	HttpSession session;
	
	// 임시로 html 연결만
	@GetMapping("")
	public String company(Model model) {
		user_session.setSessionValue(session);
		
		user_session.setSessionValue(session);
		
		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		
		return "html/company/company_page";
	}
	
	@GetMapping("/info")
	public String companyInfo() {
		return "html/company/company_info";
	}
	
	@GetMapping("/hall")
	public String companyHall() {
		return "html/company/company_hall";
	}
	
	@GetMapping("/reserve")
	public String companyReserve() {
		return "html/company/company_reserve";
	}
	
	@GetMapping("review")
	public String companyReview() {
		return "html/company/company_review";
	}
	
}
