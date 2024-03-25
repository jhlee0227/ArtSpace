package com.example.demo.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.mypage.dto.MypageDTO;
import com.example.demo.mypage.service.MypageService;

@Controller
@RequestMapping("mypage")
public class MypageController {

	@Autowired
	MypageService mypageService;
	
	
	@GetMapping("")
	public String mypage(Model model) {
		model.addAttribute("my_info", new MypageDTO());
		return "html/mypage";
	}
	
	@GetMapping("/{id}")
	public String mpage(Model model, @PathVariable("id") Integer id) {
		MypageDTO myInfo = mypageService.findByID(id);
		model.addAttribute("my_info", myInfo);
		return "html/mypage";
	}
	
	@GetMapping("/performer")
	public String performer() {
		return "html/performer_info";
	}
	
	@GetMapping("/favorite")
	public String favorite() {
		return "html/my_favorites";
	}
	
	@GetMapping("/reserve")
	public String reserve() {
		return "html/reservation_list";
	}
	
	@GetMapping("/uselist")
	public String uselist() {
		return "html/use_list";
	}
	
	@GetMapping("/review")
	public String review() {
		return "html/my_review";
	}
	
	@PostMapping("/{id}")
	public String updateNickname(@ModelAttribute MypageDTO dto, @PathVariable Integer id) {
		mypageService.updateNickname(dto);
		
		return "redirect:html/mypage";
	}
}
