package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserLoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	HttpSession session;
	
	// 로그인 화면 띄움
	@RequestMapping("login")
	public String showLogin(HttpServletRequest request) {
		Integer user_id = (Integer) session.getAttribute("user_id");		
		
		String prevURL = request.getHeader("referer").substring(22);
		if(prevURL == null) {
			session.setAttribute("prevURL", "");			
		} else {			
			if(prevURL.equals("user/welcome") || prevURL.equals("cor/welcome")) {
				session.setAttribute("prevURL", "");							
			} else {
				session.setAttribute("prevURL", request.getHeader("referer").substring(22));							
			}
		}
		
		if(user_id != null)
			return "redirect:/";
		
		return "html/login/login";
	}
	
	// 로그인 결과 처리
	@PostMapping("login/result")
	public String login_result(Model model, RedirectAttributes redirectAttributes, @RequestParam("user_id") String user_id, @RequestParam("password") String password, HttpSession session) {
		UserDTO userDTO = new UserDTO();
		try {
			userDTO.setEmail(user_id);
			userDTO.setPassword(password);
			
			userDTO = userService.login(userDTO);
			
			if(userDTO != null) {
				
				if(userDTO.getLeave_status() == 'Y') {
					model.addAttribute("errorMessage", "탈퇴 회원입니다 고객 센터에 문의 바랍니다.");
					return "html/login/login";					
				}
				
				session.setAttribute("user_id", userDTO.getUser_id());
				session.setAttribute("nickname", userDTO.getNickname());
				session.setAttribute("authority", userDTO.getAuthority());
	
				return "redirect:/" + session.getAttribute("prevURL");
			}else {
				model.addAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
//				redirectAttributes.addAttribute("status", "error");
				return "html/login/login";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
		return "redirect:/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션클리어
		return "redirect:/login";
	}
	
	@RequestMapping(value="/emailCheck" ,method = RequestMethod.POST)
	@ResponseBody
	public String idcheck(@RequestParam(value="email")  String email) {
		String chk = "";
		int result = 0;
		
		result = userService.emailCheck(email);
		
		if(result > 0) {
			chk = "redundancy"; 	// 아이디 중복
		} else if(result == 0){
			chk = "noredundancy";	// 아이디 중복 아님
		}
		return chk;
	}
	
	@RequestMapping(value="/phoneCheck" ,method = RequestMethod.POST)
	@ResponseBody
	public String phoneCheck(@RequestParam(value="phone")  String phone) {
		String chk = "";
		int result = 0;
		
		result = userService.phoneCheck(phone);
		
		if(result > 0) {
			chk = "redundancy"; 	// 아이디 중복
		} else if(result == 0){
			chk = "noredundancy";	// 아이디 중복 아님
		}
		return chk;
	}
	
	
	
	
}
