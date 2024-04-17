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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.EmailUtil;
import com.example.demo.email.EmailMessage;
import com.example.demo.user.dto.CorporationDTO;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.CorporationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cor")
public class CorporationController {

	@Autowired
	CorporationService corporationService;

	@Autowired
	private EmailUtil emailUtil;

	// 법인 회원가입인증 화면 띄움
	@GetMapping("/corporation")
	public String showCorporationCode() {
		return "html/login/join_corporation";
	}

	// 법인 데이터 체크하고 정보 입력창으로
	@PostMapping("/corporation_code/insert")
	public String checkCorCode(Model model, @RequestParam("company_number") String companyNumber) {
		UserDTO user = new UserDTO();
		corporationService.insertUser(user);

		CorporationDTO corpor = new CorporationDTO();
		corpor.setCompany_number(companyNumber);
		corpor.setUser_id(user.getUser_id());

		corporationService.insertCompanyNumber(corpor);

		model.addAttribute("user_id", user.getUser_id());

		return "html/login/join_corporation_info";
	}

	// 법인 회원가입인증코드 화면 띄움
	@PostMapping("/insert")
	public String scInsert(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {
		corporationService.updateUser(userDTO);

		// 인증 코드 메일 보내기
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setTo(userDTO.getEmail());
		String emailCode = emailUtil.sendJoinMail(emailMessage);
		// 세션에 인증 코드 저장
		session.setAttribute("emailCode", emailCode);

		// html에 emailCode, email 렌더링
		model.addAttribute("emailCode", emailCode);
		model.addAttribute("email", userDTO.getEmail());

		return "html/login/join_corporation_code";
	}

//	@GetMapping("/join_code")
//	public String codeCor() {
//		return "html/login/join_corporation_code";
//	} 

	// 인증 메일 재발송
	@PostMapping("/resend")
	public ResponseEntity<String> resend(@RequestBody Map<String, String> requestBody, HttpSession session,
			Model model) {
		String email = requestBody.get("email");
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setTo(email);
		String newEmailCode = emailUtil.sendJoinMail(emailMessage);

		session.setAttribute("emailCode", newEmailCode);
		model.addAttribute("emailCode", newEmailCode);

		return ResponseEntity.ok(newEmailCode);
	}

	// 법인 회원가입 완료 화면 띄움
	@PostMapping("/welcome")
	public String welcomeCor() {
		return "html/login/join_corporation_welcome";
	}

}
