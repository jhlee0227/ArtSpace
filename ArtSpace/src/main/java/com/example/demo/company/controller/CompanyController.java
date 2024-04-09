package com.example.demo.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.SessionUtil;
import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.company.service.CompanyService;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReservationDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.hall.service.HallService;
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
	
	@Autowired
	HallService hallService;

	// 공통부분 정리
	private void myInfo (Model model) {
	    user_session.setSessionValue(session);
	    UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
	    model.addAttribute("my_info", myInfo);
	    model.addAttribute("user_id", user_session.getUser_id());
	    model.addAttribute("nickname", user_session.getNickname());
	}
	
	// 법인 마이페이지 기본
	@GetMapping("")
	public String company(Model model) {
		myInfo(model);

		String authority = user_session.getAuthority();
		if (authority.equals("SCN") || authority.equals("SCY")) {			
			return "html/company/company_page";
		} else {
			return "redircet:/";
		}

		
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

	// 사업자정보
	@GetMapping("/info")
	public String companyInfo(Model model) {
		myInfo(model);
		
		CompanyDTO comDTO = companyService.findByID(user_session.getUser_id());
		model.addAttribute("com_info", comDTO);
		int company_id = comDTO.getCompany_id();
		// 파일 제출 여부를 알기 위한 제출 file count
		int fileCount = companyService.fileCount(company_id);
		model.addAttribute("fileCount", fileCount);
		
		
		return "html/company/company_info";
	}
	
	// 사업자정보 등록
	@PostMapping("/info")
	public String companyInfoUpdate(@ModelAttribute CompanyDTO dto) {
		user_session.setSessionValue(session);
		
		dto.setUser_id(user_session.getUser_id());
		companyService.updateInfo(dto);
		return "redirect:/company/info";
	}

	// 등록한 공연장 목록
	@GetMapping("/hall")
	public String companyHall(Model model) {
		myInfo(model);

		List<HallDTO> myHall = companyService.getHall(user_session.getUser_id());
		model.addAttribute("my_hall", myHall);
		return "html/company/company_hall";
	}
	
	// 공연장 정보 수정
	@PostMapping("form/{id}")
	public String hallUpdate(Model model, @PathVariable("id") Integer id) {
		
		HallDTO hallInfo = hallService.findById(id);
		model.addAttribute("hall_info", hallInfo);
		
		return "redirect:/hall/form" + id;
	}

	// 공연장 정보 삭제
	@PostMapping("/hall/delete")
	public String hallDelete(@RequestParam("hall_id") Integer hall_id) {
		
		companyService.hallDelete(hall_id);
		
		return "redirect:/company/hall";
	}
	
	// 해당 법인이 등록한 공연장에 대한 예약 정보
	@GetMapping("/reserve")
	public String companyReserve(Model model) {
		myInfo(model);
		
		List<ReservationDTO> reserveList = companyService.getReserve(user_session.getUser_id());
		model.addAttribute("reserve_list", reserveList);
		return "html/company/company_reserve";
	}

	// 해당 공연장에 대한 예약 삭제
	@PostMapping("/reserve/delete")
	public String reserveDelete(Model model, @RequestParam("reserve_id") Integer reserve_id) {
		
		companyService.reserveDelete(reserve_id);
		return "redirect:/company/reserve";
	}

}
