package com.example.demo.mypage.controller;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.SessionUtil;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.mypage.dto.LikeDTO;
import com.example.demo.mypage.dto.PerformerDTO;
import com.example.demo.mypage.service.MypageService;
import com.example.demo.user.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mypage")
public class MypageController {

	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	MypageService mypageService;

	@Autowired
	HttpSession session;

	// 마이페이지 내 정보
	@GetMapping("")
	public String mypage(Model model) {
		
		user_session.setSesstionValue(session);
		
		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		return "html/mypage/mypage";
	}

	// 닉네임 수정
	@PostMapping("/update/nickname")
	public String updateNickname(@ModelAttribute UserDTO dto) {

		user_session.setSesstionValue(session);
		
		dto.setUser_id(user_session.getUser_id());
		mypageService.updateNickname(dto);
		return "redirect:/mypage";
	}

	// 패스워드 수정
	@PostMapping("/update/pw")
	public String updatePw(@ModelAttribute UserDTO dto) {

		user_session.setSesstionValue(session);

		dto.setUser_id(user_session.getUser_id());
		mypageService.updatePw(dto);
		return "redirect:/mypage";
	}

	// 공연자 정보 기본
	@GetMapping("/performer")
	public String performer(Model model) {
		user_session.setSesstionValue(session);

		PerformerDTO perfoInfo = mypageService.findByPID(user_session.getUser_id());
		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("perfo_info", perfoInfo);
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		return "html/mypage/performer_info";
	}

	// 공연자 정보 등록 및 수정
	@PostMapping("/performer")
	public String insertPerformer(@ModelAttribute PerformerDTO dto) {
		user_session.setSesstionValue(session);
		
		dto.setUser_id(user_session.getUser_id());
		mypageService.insert(dto);
		return "redirect:/mypage/performer";
	}

	// 내 즐겨찾기
	@GetMapping("/favorite")
	public String favorite(Model model) {
		user_session.setSesstionValue(session);
		
		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		List<HallDTO> likeList = mypageService.getAllLike(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("like_list", likeList);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		return "html/mypage/my_favorites";
	}

	// 찜 삭제
	@PostMapping("/favorite/delete")
	public String likeDelete(Model model, @RequestParam("hall_id") Integer hall_id) {
		user_session.setSesstionValue(session);

	    mypageService.likeDelete(user_session.getUser_id(), hall_id);
	    return "redirect:/mypage/favorite";
	}

	// 예약 내역
	@GetMapping("/reserve")
	public String reserve(Model model) {
		user_session.setSesstionValue(session);
		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		
		List<HallDTO> reserveList = mypageService.getAllReserve(user_session.getUser_id());
		model.addAttribute("reserve_list", reserveList);
		return "html/mypage/reservation_list";
	}

	// 이용 내역
	@GetMapping("/uselist")
	public String uselist() {
		return "html/mypage/use_list";
	}

	// 리뷰 작성 및 작성 내역
	@GetMapping("/review")
	public String review() {
		return "html/mypage/my_review";
	}

}
