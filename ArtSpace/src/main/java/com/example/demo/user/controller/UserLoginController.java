package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserLoginController {
	
	@Autowired
	private UserService userService;
	
	// 로그인 화면 띄움
	@GetMapping("login")
	public String showLogin() {
		return "html/login/login";
	}
	
	// 로그인 결과 처리
	@PostMapping("login/result")
	public ModelAndView login_result(ModelAndView mv, @RequestParam("user_id") String user_id, @RequestParam("password") String password, HttpSession session) {
		UserDTO userDTO = new UserDTO();
		try {
			userDTO.setEmail(user_id);
			userDTO.setPassword(password);
			
			String nickname = userService.login(userDTO);
			System.out.println(nickname);
			
			if(nickname !=null) {
				session.setAttribute("user_id", userDTO.getUser_id());
				session.setAttribute("nickname", nickname);
				mv.setViewName("redirect:/");
			}else {
				mv.addObject("status", 0);
				mv.setViewName("html/login/login");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션클리어
		return "redirect:/login";
	}
	
	
}
