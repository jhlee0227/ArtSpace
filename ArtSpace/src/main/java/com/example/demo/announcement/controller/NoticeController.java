package com.example.demo.announcement.controller;

import java.time.LocalDate;
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
import com.example.demo.announcement.dto.AskDto;
import com.example.demo.announcement.dto.NoticeDto;
import com.example.demo.announcement.service.AskService;
import com.example.demo.announcement.service.NoticeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	HttpSession session;

	SessionUtil user_session = new SessionUtil();
	
	@GetMapping("")
	public String showboardPage(Model model) {
		List<NoticeDto> noticeList = noticeService.getNotice();
		model.addAttribute("noticeList", noticeList);
		
		user_session.setSessionValue(session);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		model.addAttribute("authority", user_session.getAuthority());
		return "html/announcement/notice";	
	}
	
	@GetMapping("/write")
	public String showWrite(Model model) {
		NoticeDto notice = new NoticeDto();
		model.addAttribute("notice", notice);
		return "html/announcement/board_notice";
	}
	

	// 공지사항 등록과 수정
	@PostMapping("/insert")
	public String insertUser(@ModelAttribute NoticeDto noticeDTO, @RequestParam(value = "announ_id", required = false) Integer announ_id) {
		 if (announ_id != null) {
		        noticeDTO.setCreate_date(LocalDate.now());
		        noticeService.update(noticeDTO);
		    } else {
		        noticeDTO.setCreate_date(LocalDate.now());
		        noticeService.insert(noticeDTO);
		    }
		return "redirect:/notice";
	}
	

}
