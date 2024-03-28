package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.admin.service.AdminService;
import com.example.demo.user.dto.UserDTO;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	// 기본 관리자 페이지
//	@GetMapping("")
//	public String admin() {
//		return "html/admin";
//	}
	
	@GetMapping("")
	public String admin(Model model) {
		List<UserDTO> userList = adminService.getAllUsers();
		model.addAttribute("user_list", userList);
		return "html/admin/admin";
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
