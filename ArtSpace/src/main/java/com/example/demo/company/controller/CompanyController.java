package com.example.demo.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.SessionUtil;
import com.example.demo.company.service.CompanyService;
import com.example.demo.hall.dto.HallDTO;
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
	CompanyService companyService;

	@Autowired
	HttpSession session;

	// 공통부분 정리
//	private void mypageInfo (Model model) {
//	    user_session.setSessionValue(session);
//	    UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
//	    model.addAttribute("my_info", myInfo);
//	    model.addAttribute("user_id", user_session.getUser_id());
//	    model.addAttribute("nickname", user_session.getNickname());
//	}
	
	// 법인 마이페이지 기본
	@GetMapping("")
	public String company(Model model) {
		user_session.setSessionValue(session);

		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());

		return "html/company/company_page";
	}

	// 닉네임 수정
	@PostMapping("/update/nickname")
	public String updateNickname(@ModelAttribute UserDTO dto) {

		user_session.setSessionValue(session);

		dto.setUser_id(user_session.getUser_id());
		mypageService.updateNickname(dto);
		session.setAttribute("nickname", dto.getNickname());
		return "redirect:/mypage";
	}

	// 패스워드 수정
	@PostMapping("/update/pw")
	public String updatePw(@ModelAttribute UserDTO dto) {

		user_session.setSessionValue(session);

		dto.setUser_id(user_session.getUser_id());
		mypageService.updatePw(dto);
		return "redirect:/mypage";
	}

	// 핸드폰번호 수정
	@PostMapping("/update/phone")
	public String updatePhone(@ModelAttribute UserDTO dto) {

		user_session.setSessionValue(session);
		dto.setUser_id(user_session.getUser_id());
		mypageService.updatePhone(dto);
		return "redirect:/mypage";
	}

	@GetMapping("/info")
	public String companyInfo(Model model) {
		user_session.setSessionValue(session);

		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		return "html/company/company_info";
	}

	@GetMapping("/hall")
	public String companyHall(Model model) {
		user_session.setSessionValue(session);

		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());

		List<HallDTO> myHall = companyService.getHall(user_session.getUser_id());
		model.addAttribute("my_hall", myHall);
		return "html/company/company_hall";
	}

	@GetMapping("/reserve")
	public String companyReserve(Model model) {
		user_session.setSessionValue(session);

		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		return "html/company/company_reserve";
	}

	@GetMapping("review")
	public String companyReview(Model model) {
		user_session.setSessionValue(session);

		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		return "html/company/company_review";
	}

}
