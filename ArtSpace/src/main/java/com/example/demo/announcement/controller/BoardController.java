package com.example.demo.announcement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.announcement.service.BoardService;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("")
	public String showboardPage() {
		return "html/announcement/board";
		
	}
	
	
	@PostMapping("insert")
	public String insertUser(@ModelAttribute UserDTO userDTO) {
		
		userBoard.insert(userDTO);
		return "html/announcement/boder_notice";
	}
}
