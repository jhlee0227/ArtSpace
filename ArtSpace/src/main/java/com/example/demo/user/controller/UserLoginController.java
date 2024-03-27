//package com.example.demo.user.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.example.demo.user.dto.MemberDTO;
//import com.example.demo.user.service.memberService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class UserLoginController {
//
//	
//	@RequestMapping("/login")
//	public String login() {
//		return "login";
//	}
//	
//	@PostMapping("login_result")
//	public ModelAndView login_result(@RequestParam String userid,
//			String passwd, MemberDTO dto, HttpSession session,
//			ModelAndView mv) {
//		try {
//			dto.setUserId(userid);
//			dto.setPassWd(passwd);
//			
//			String name = memberService.login(dto);
//			if(name !=null) {
//				session.setAttribute("userid", dto.getUserId());
//				session.setAttribute("name", name);
//				mv.setViewName("redirect:/product/list");
//				mv.addObject("message", "success");
//			}else {
//				mv.addObject("message", "error");
//				mv.setViewName("redirect:/member/login");
//			}
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return mv;
//	}
//	
//	@RequestMapping("logout")
//	public String logout(HttpSession session) {
//		session.invalidate(); // 세션클리어
//		return "redirect:/member/login?message=logout";
//	}
//	
//	
//}
