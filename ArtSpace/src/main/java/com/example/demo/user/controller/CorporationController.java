package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.user.dto.CorporationDTO;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.CorporationService;

@Controller
@RequestMapping("cor")
public class CorporationController {

	@Autowired
	CorporationService corporationService;
	
	// 법인 회원가입인증 화면 띄움
	@GetMapping("corporation")
	public String showCorporationCode() {
		return "html/login/join_corporation";
	} 

	// 법인 데이터 체크하고 정보 입력창으로
	@PostMapping("corporation_code/insert")
	public String checkCorCode(Model model, @RequestParam("company_number") String companyNumber) {
		UserDTO user = new UserDTO();
		corporationService.insertUser(user);
		
		CorporationDTO corpor = new CorporationDTO();
		corpor.setCompany_number(companyNumber);
		corpor.setUser_id(user.getUser_id());
		
		corporationService.insertCompanyNumber(corpor);
		
		model.addAttribute("user_id", user.getUser_id());
		
		return "html/login/join_corporation_info";
	}
		
	@PostMapping("insert")
	public String scInsert(@ModelAttribute UserDTO userDTO) {
		corporationService.updateUser(userDTO);
		return "redirect:/cor/join_code";
	}
	
	
	// 법인 회원가입인증코드 화면 띄움
	@GetMapping("join_code")
	public String codeCor() {
		return "html/login/join_corporation_code";
	} 
	
	@PostMapping("joincode_check")
	public String codeCheck() {
		return "redirect:/cor/welcome";
	}
	
	// 법인 회원가입 완료 화면 띄움
	@GetMapping("welcome")
	public String welcomeCor() {
		return "html/login/join_corporation_welcome";
	} 
	
}
