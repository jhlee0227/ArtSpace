package com.example.demo.admin.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.SessionUtil;
import com.example.demo.admin.service.AdminService;
import com.example.demo.announcement.dto.NoticeDto;
import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.company.dto.CompanyFileDTO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.mypage.service.MypageService;
import com.example.demo.user.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {

	SessionUtil user_session = new SessionUtil();

	@Autowired
	AdminService adminService;

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

	// 회원관리 : 유저 정보 불러오기
	@GetMapping("")
	public String admin(Model model) {
		myInfo(model);
		List<UserDTO> userList = adminService.getAllUsers();
		model.addAttribute("user_list", userList);
		String authority = user_session.getAuthority();
		if (user_session.getUser_id() != null) {
			if (authority.equals("SA")) {
				return "html/admin/admin";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/login";
		}

	}

	// 회원관리 : 선택한 유저 탈퇴시키기
	@PostMapping("/leave")
	public String leave(@RequestParam(value = "check1", required = false) List<Integer> selectUser1,
			@RequestParam(value = "check2", required = false) List<Integer> selectUser2) {

		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		// 전체 회원 탭
		if (selectUser1 != null) {
			for (Integer user_id : selectUser1) {
				adminService.leave(user_id);
			}
		}
		// 가입 중 회원 탭
		if (selectUser2 != null) {
			for (Integer user_id : selectUser2) {
				adminService.leave(user_id);
			}
		}

		return "redirect:/admin";
	}

	// 회원관리 : 선택한 유저 탈퇴 해제
	@PostMapping("/resign")
	public String resign(@RequestParam(value = "check1", required = false) List<Integer> selectUser1,
			@RequestParam(value = "check3", required = false) List<Integer> selectUser3) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		// 전체 회원 탭
		if (selectUser1 != null) {
			for (Integer user_id : selectUser1) {
				adminService.resign(user_id);
			}
		}
		// 차단 회원 탭
		if (selectUser3 != null) {
			for (Integer user_id : selectUser3) {
				adminService.resign(user_id);
			}
		}

		return "redirect:/admin";
	}

	// 검색으로 회원조회
	@PostMapping("/search")
	@ResponseBody
	public List<UserDTO> searchUsers(@RequestBody Map<String, String> data) {
		String type = data.get("type");
		String keyword = data.get("keyword");
		List<UserDTO> userList = adminService.searchUsers(type, keyword);
		return userList;
	}

	// 법인 회원 승인 요청 조회
	@GetMapping("/company")
	public String approveCompany(Model model) {
		myInfo(model);

		String authority = user_session.getAuthority();
		if (user_session.getUser_id() != null) {
			if (authority.equals("SA")) {
				List<CompanyDTO> comList = adminService.getCompany();
				model.addAttribute("com_list", comList);

				for (CompanyDTO company : comList) {
					int companyId = company.getCompany_id();
					List<CompanyFileDTO> comFileList = adminService.getCompanyFile(companyId);
					model.addAttribute("com_file_list_" + companyId, comFileList);
				}
				return "html/admin/admin_company";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/login";
		}
	}

	
	// 법인 회원 승인
	@PostMapping("/company/approve")
	public String approve(@RequestParam(value = "check1") List<Integer> selectCompany) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}

		if (selectCompany != null) {
			for (Integer user_id : selectCompany) {
				adminService.approve(user_id);
			}
		}

		return "redirect:/admin/company";
	}

	// 법인 회원 승인 거부
	@PostMapping("/company/unapprove")
	public String unapprove(@RequestParam(value = "check1") List<Integer> selectCompany) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}

		if (selectCompany != null) {
			for (Integer user_id : selectCompany) {
				Integer company_id = adminService.findCompanyById(user_id);
				adminService.unapprove(company_id);
			}
		}

		return "redirect:/admin/company";
	}

	// 공연장 정보 목록 조회
	@GetMapping("/hallinfo")
	public String hallinfo(Model model) {
		myInfo(model);

		String authority = user_session.getAuthority();
		if (user_session.getUser_id() != null) {
			if (authority.equals("SA")) {
				List<HallDTO> hallList = adminService.getAllHalls();
				model.addAttribute("hall_list", hallList);

				return "html/admin/hall_info";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/login";
		}
	}

	// 공연장 검색
	@PostMapping("/hall/search")
	@ResponseBody
	public List<HallDTO> searchHalls(@RequestBody Map<String, String> data) {
		String type = data.get("type");
		String keyword = data.get("keyword");
		List<HallDTO> searchHallList = adminService.getHalls(type, keyword);
		return searchHallList;
	}

	// 공연장 차단
	@PostMapping("/hall/block")
	public String hallBlock(@RequestParam("check") List<Integer> selectHall) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}

		if (selectHall != null) {
			for (Integer hall_id : selectHall) {
				adminService.hallBlock(hall_id);
			}
		}

		return "redirect:/admin/hallinfo";
	}

	// 공연장 차단 해제
	@PostMapping("/hall/unblock")
	public String hallUnblock(@RequestParam("check") List<Integer> selectHall) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}

		if (selectHall != null) {
			for (Integer hall_id : selectHall) {
				adminService.hallUnblock(hall_id);
			}
		}

		return "redirect:/admin/hallinfo";
	}

	// 공지사항 목록 조회
	@GetMapping("/notice")
	public String notice(Model model) {
		myInfo(model);

		String authority = user_session.getAuthority();
		if (user_session.getUser_id() != null) {
			if (authority.equals("SA")) {
				List<NoticeDto> noticeList = adminService.getAllNotice();
				model.addAttribute("notice_list", noticeList);

				return "html/admin/admin_notice";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/login";
		}
	}

	// 공지사항 상세 조회
	@GetMapping("/notice/{id}")
	public String noticeDetail(Model model, @PathVariable("id") Integer id) {

		NoticeDto notice = adminService.findNoticeById(id);
		notice.setCreate_date(LocalDate.now());
		model.addAttribute("notice", notice);
		return "html/announcement/board_notice";
	}

	// 공지사항 삭제
	@PostMapping("/notice/delete")
	public String noticeDelete(@RequestParam("check") List<Integer> selectNotice) {

		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		
		if (selectNotice != null) {
			for (Integer announ_id : selectNotice) {
				adminService.noticeDelete(announ_id);
			}
		}

		return "redirect:/admin/notice";
	}

}
