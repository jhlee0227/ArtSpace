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

import com.example.demo.announcement.dto.BoardDto;
import com.example.demo.announcement.service.BoardService; 
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("")
	public String showboardPage(Model model) {
		List<BoardDto> noticeList = boardService.getNotice();
		model.addAttribute("noticeList", noticeList);
		return "html/announcement/board";	
	}
	
	@GetMapping("write")
	public String showWrite() {
		return "html/announcement/board_notice";
	}
	
	@PostMapping("/insert")
	public String insertUser(@ModelAttribute BoardDto boardDTO) {
		boardDTO.setCreate_date(LocalDate.now());
		boardService.insert(boardDTO);
		return "html/announcement/board_notice";
	}
}
