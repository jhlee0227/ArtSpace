package com.example.demo.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("company")
public class CompanyController {

	@GetMapping("")
	public String company() {
		return "html/company_page";
	}
	
	@GetMapping("/info")
	public String companyInfo() {
		return "html/company_info";
	}
	
	@GetMapping("/hall")
	public String companyHall() {
		return "html/company_hall";
	}
	
	@GetMapping("/reserve")
	public String companyReserve() {
		return "html/company_reserve";
	}
	
	@GetMapping("review")
	public String companyReview() {
		return "html/company_review";
	}
	
}
