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

import com.example.demo.announcement.dto.NoticeDto;

import com.example.demo.announcement.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("")
	public String showboardPage(Model model) {
		List<NoticeDto> noticeList = noticeService.getNotice();
		model.addAttribute("noticeList", noticeList);
		return "html/announcement/notice";	
	}
	
	@GetMapping("/write")
	public String showWrite() {
		return "html/announcement/board_notice";
	}
	
	@PostMapping("/insert")
	public String insertUser(@ModelAttribute NoticeDto noticeDTO) {
		noticeDTO.setCreate_date(LocalDate.now());
		noticeService.insert(noticeDTO);
		return "html/announcement/board_notice";
	}
}
