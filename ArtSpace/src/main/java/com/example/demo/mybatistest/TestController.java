package com.example.demo.mybatistest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@GetMapping("/t")
	public String test(Model model) {
		model.addAttribute("list", testService.getAll());		
		return "html/test1";
	}
	
}
