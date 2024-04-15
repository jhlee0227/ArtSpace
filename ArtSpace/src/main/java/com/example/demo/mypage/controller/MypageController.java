package com.example.demo.mypage.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.SessionUtil;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReservationDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.mypage.dto.LikeDTO;
import com.example.demo.mypage.dto.PerformerDTO;
import com.example.demo.mypage.service.MypageService;
import com.example.demo.user.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	MypageService mypageService;

	@Autowired
	HttpSession session;

	// 공통부분 (기본정보)
	public void myInfo(Model model) {
		user_session.setSessionValue(session);
		UserDTO myInfo = mypageService.findByID(user_session.getUser_id());
		model.addAttribute("my_info", myInfo);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
	}
	
	// 마이페이지 내 정보
	@GetMapping("")
	public String mypage(Model model) {
		
		myInfo(model);
		String authority = user_session.getAuthority();
		if (user_session.getUser_id() != null) {
			if (authority.equals("SU")) {
				return "html/mypage/mypage";
			} else if (authority.equals("SCN") || authority.equals("SCY")) {
				return "redirect:/company";
			} else if (authority.equals("SA")) {
				return "redirect:/admin";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/login";
		}
		
		
	}

	// 닉네임 수정
	@PostMapping("/update/nickname")
	public String updateNickname(@ModelAttribute UserDTO dto) {

		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		
		dto.setUser_id(user_session.getUser_id());
		mypageService.updateNickname(dto);
		session.setAttribute("nickname", dto.getNickname());
		return "redirect:/mypage";
	}

	// 패스워드 수정
	@PostMapping("/update/pw")
	public String updatePw(@ModelAttribute UserDTO dto) {

		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		
		dto.setUser_id(user_session.getUser_id());
		mypageService.updatePw(dto);
		return "redirect:/mypage";
	}

	// 핸드폰번호 수정
	@PostMapping("/update/phone")
	public String updatePhone(@ModelAttribute UserDTO dto) {
		
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		
		dto.setUser_id(user_session.getUser_id());
		mypageService.updatePhone(dto);
		return "redirect:/mypage";
	}
	
	// 공연자 정보 기본
	@GetMapping("/performer")
	public String performer(Model model) {
		myInfo(model);

		PerformerDTO perfoInfo = mypageService.findByPID(user_session.getUser_id());
		model.addAttribute("perfo_info", perfoInfo);
		
		if (user_session.getUser_id() != null) {
			if (user_session.getAuthority().equals("SU")) {
				return "html/mypage/performer_info";
			} else {
				return "html/index";
			}
		} else {
			return "redirect:/login"; 
		}
		
	}

	// 공연자 정보 등록 및 수정
	@PostMapping("/performer")
	public String insertPerformer(@ModelAttribute PerformerDTO dto) {
		user_session.setSessionValue(session);
		
		dto.setUser_id(user_session.getUser_id());
		mypageService.insert(dto);
		return "redirect:/mypage/performer";
	}

	// 내 즐겨찾기
	@GetMapping("/favorite")
	public String favorite(Model model) {
		myInfo(model);
		
		List<HallDTO> likeList = mypageService.getAllLike(user_session.getUser_id());
		model.addAttribute("like_list", likeList);
		
		if (user_session.getUser_id() != null) {
			return "html/mypage/my_favorites";			
		} else {
			return "redirect:/login";
		}
	}

	// 찜 삭제
	@PostMapping("/favorite/delete")
	public String likeDelete(@RequestParam("hall_id") Integer hall_id) {
		user_session.setSessionValue(session);

	    mypageService.likeDelete(user_session.getUser_id(), hall_id);
	    return "redirect:/mypage/favorite";
	}

	// 예약 내역
	@GetMapping("/reserve")
	public String reserve(Model model) {
		myInfo(model);
		
		List<ReservationDTO> reserveList = mypageService.getAllReserve(user_session.getUser_id());
		model.addAttribute("reserve_list", reserveList);
		
		if (user_session.getUser_id() != null) {
			return "html/mypage/reservation_list";			
		} else {
			return "redirect:/login";
		}
	}
	
	// 예약 상세
	@PostMapping("/reserve")
    @ResponseBody
    public Map<String, Object> reserveDetail(@RequestParam("reserve_id") Integer reserve_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ReservationDTO reservationDetail = mypageService.reserveDetail(reserve_id);
            List<ReservationDTO> reservationEquipments = mypageService.reserveEquip(reserve_id);
            response.put("reservationDetail", reservationDetail);
            response.put("reservationEquipments", reservationEquipments);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
	
	

	// 예약 취소	
	@PostMapping("/reserve/delete")
	public String reserveDelete(Model model, @RequestParam("reserve_id") Integer reserve_id) {
		
		mypageService.reserveDelete(reserve_id);
		return "redirect:/mypage/reserve";
	}
	

	// 리뷰 작성 및 작성한 리뷰 조회
	@GetMapping("/review")
	public String review(Model model) {
		myInfo(model);
		
		// 리뷰 작성가능한(리뷰를 아직 작성하지 않은) 예약목록
		List<ReservationDTO> notReviewList = mypageService.getNotReview(user_session.getUser_id());
		model.addAttribute("notReview_list", notReviewList);
		
		// 작성한 리뷰 목록
		List<ReviewDTO> reviewList = mypageService.getReview(user_session.getUser_id());
		model.addAttribute("review_list", reviewList);
		
		if (user_session.getUser_id() != null) {
			return "html/mypage/my_review";
		} else {
			return "redirect:/login";
		}
	}

	// 리뷰 작성
	@PostMapping("/saveReview")
	@ResponseBody
	public boolean saveReview(@RequestBody ReviewDTO review) {
		try {
			user_session.setSessionValue(session);
			review.setUser_id(user_session.getUser_id());
			review.setCreate_date(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            mypageService.saveReview(review);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	// 리뷰 목록 업데이트
	@PostMapping("/updateReviewStatus")
	@ResponseBody
	public boolean updateReviewStatus(@RequestParam("reserve_id") Integer reserve_id) {
		try {
			mypageService.updateReviewStatus(reserve_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
