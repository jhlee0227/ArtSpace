package com.example.demo.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.EmailUtil;
import com.example.demo.email.EmailMessage;
import com.example.demo.email.EmailResponseDto;
import com.example.demo.email.EmailService;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailUtil emailUtil;

	// 일반 회원가입 화면 띄움
	@GetMapping("/join")
	public String showJoin() {
		return "html/login/join_user_info";
	}

	// 유저 데이터 insert 처리 후 메일 인증 코드 부분으로 보냄
	@PostMapping("/insert")
	public String insertUser(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {
		// 유저 데이터 insert
		userService.insert(userDTO);

		// 인증 코드 메일 보내기
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setTo(userDTO.getEmail());
		String emailCode = emailUtil.sendJoinMail(emailMessage);
		// 세션에 인증 코드 저장
		session.setAttribute("emailCode", emailCode);

		// html에 emailCode, email 렌더링
		model.addAttribute("emailCode", emailCode);
		model.addAttribute("email", userDTO.getEmail());
		
		return "html/login/join_user_code";
	}

	// 인증 메일 재발송
//	@RequestMapping(value = "/resend", method = {RequestMethod.GET, RequestMethod.POST})
//	public String resend(HttpSession session, @RequestBody Map<String, String> requestBody) {
//		String email = requestBody.get("email");
//		EmailMessage emailMessage = new EmailMessage();
//		emailMessage.setTo(email);
//	    String newEmailCode = emailUtil.sendJoinMail(emailMessage);
//	    
//	    session.setAttribute("emailCode", newEmailCode);
//	    
//		return "redirect:/user/insert";
//	}
	
	@PostMapping("/resend")
	public ResponseEntity<String> resend(@RequestBody Map<String, String> requestBody, HttpSession session, Model model) {
	    String email = requestBody.get("email");
	    EmailMessage emailMessage = new EmailMessage();
		emailMessage.setTo(email);
	    String newEmailCode = emailUtil.sendJoinMail(emailMessage);
	    
	    session.setAttribute("emailCode", newEmailCode);
	    model.addAttribute("emailCode", newEmailCode);

	    return ResponseEntity.ok(newEmailCode);
	}
	
	// 회원가입 완료 페이지 오픈
	@PostMapping("/welcome")
	public String welcome() {

		return "html/login/join_user_welcome";
	}

}
