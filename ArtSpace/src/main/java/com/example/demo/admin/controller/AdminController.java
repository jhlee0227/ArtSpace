package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.admin.service.AdminService;
import com.example.demo.user.dto.UserDTO;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	// 회원관리 : 유저 정보 불러오기
	@GetMapping("")
	public String admin(Model model) {
		List<UserDTO> userList = adminService.getAllUsers();
		model.addAttribute("user_list", userList);
		return "html/admin/admin";
	}

	// 회원관리 : 선택한 유저 탈퇴시키기
	@PostMapping("/leave")
	public String leave(@RequestParam("check1") List<Integer> selectUser) {
		for (Integer user_id : selectUser) {
			adminService.leave(user_id);
		}
		return "redirect:/admin";
	}

	// 회원관리 : 선택한 유저 탈퇴 해제
	@PostMapping("/resign")
	public String resign(@RequestParam(value = "check1", required = false) List<Integer> selectUser1,
						 @RequestParam(value = "check2", required = false) List<Integer> selectUser2) {
		// 전체 회원 탭
		if (selectUser1 != null) {
			for (Integer user_id : selectUser1) {
				adminService.resign(user_id);
			}
		}
		// 차단 회원 탭
		if (selectUser2 != null) {
			for (Integer user_id : selectUser2) {
				adminService.resign(user_id);
			}
		}

		return "redirect:/admin";
	}

	// 임시 - html 연결만
	@GetMapping("/hallinfo")
	public String hallinfo() {
		return "html/admin/hall_info";
	}

	@GetMapping("/notice")
	public String notice() {
		return "html/admin/admin_notice";
	}

	@GetMapping("/review")
	public String review() {
		return "html/admin/admin_review";
	}

}
