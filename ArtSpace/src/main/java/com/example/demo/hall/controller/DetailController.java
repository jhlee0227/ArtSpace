package com.example.demo.hall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.service.DetailService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("hall")
public class DetailController {
	
	@Autowired
	DetailService detailService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("detail")
	public String detailPage(Model model, HttpSession session) {
		Integer id = (Integer) session.getAttribute("hall_id");
		HallDTO detailID = detailService.findById(id);
		model.addAttribute("detailID", detailID);
		return "html/hall/detail_page";
	}

}
