package com.example.demo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.admin.dto.AdminDTO;
import com.example.demo.admin.service.AdminLoginService;
import com.example.demo.user.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

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
	
	@PostMapping("insert")
	public String insert(@ModelAttribute AdminDTO adminDTO) {
		adminLoginService.insert(adminDTO);
		return "redirect:/yjh-console";
	}
	
	
	// 로그인 결과 처리
	@PostMapping("result")
	public String login_result(Model model, RedirectAttributes redirectAttributes, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		AdminDTO adminDTO = new AdminDTO();
		try {
			adminDTO.setEmail(email);
			adminDTO.setPassword(password);
			
			adminDTO = adminLoginService.login(adminDTO);
			
			if(adminDTO != null) {
				session.setAttribute("user_id", adminDTO.getAdmin_id());
				session.setAttribute("nickname", adminDTO.getNickname());
				session.setAttribute("authority", adminDTO.getAuthority());
				return "redirect:/";
			}else {
				model.addAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return "html/login/admin_login";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
		return "redirect:/yjh-console";
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
