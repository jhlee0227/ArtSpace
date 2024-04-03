package com.example.demo.hall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.SessionUtil;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.service.HallListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HallListController {

	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HallListService hallListService;

	@GetMapping("/list")
	public String showHallList(Model model) {
		List<HallDTO> hallList = hallListService.getList("create_date");
		model.addAttribute("hallList", hallList);
		
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		
		String[] regName = {"서울", "경기", "인천", "부산", "대전", "대구", "울산", "광주", "강원", "충북", "충남", "경북", "경남", "전북","전남", "제주"}; 
		String[][] region1 = {
				{"서울 전체", "강남구", "강동구", "강북구" ,"강서구" ,"관악구" ,"광진구" ,"구로구" ,"금천구" ,"노원구" ,"도봉구" ,"동대문" ,"동작구" ,"마포구" ,"서대문구", "서초구" ,"성동구" ,"성북구" ,"송파구" ,"양천구" ,"영등포구" ,"용산구" ,"은평구" ,"종로구" ,"중구", "중랑구"},
				{"경기 전체", "가평", "고양", "과천", "광명", "광주" ,"구리" ,"군포" ,"김포", "남양주", "동두천", "부천", "성남", "수원", "시흥", "안산", "안성", "안양" ,"양주", "양평", "여주", "연천", "오산", "용인", "의왕", "의정부", "이천", "파주", "평택", "포천", "하남", "화성"},
				{"인천 전체", "강화군", "계양군", "남동구", "동구", "미추홀구", "부평구", "서구", "연수구", "옹진군", "중구"},
				{"부산 전체", "강서구", "금정구", "기장군", "남구", "동구", "동래구" , "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구"},
				{"대전 전체", "대덕구", "동구", "서구", "유성구", "중구"},
				{"대구 전체", "남구", "달서구", "달성군", "동구", "북구", "서구", "수성구", "중구"},
				{"울산 전체", "남구", "동구", "북구", "울주구", "중구"},
				{"광주 전체", "광산구", "남구", "동구", "북구", "서구"},
				{"강원 전체", "강릉", "고성", "동해", "삼척", "속초", "양구", "양양", "영월", "원주", "인제", "정선", "철원", "춘천", "태백", "평창", "홍천", "화천", "횡성"},
				{"충북 전체", "괴산", "단양", "보은", "영동", "옥천", "음성", "제천", "증평", "진천", "청주", "충주"},
				{"충남 전체", "계룡", "공주", "금산", "논산", "당진", "보령", "부여", "서산", "서천", "아산", "예산", "천안", "청양", "태안", "홍성"},
				{"경북 전체", "경산", "경주", "고령", "구미", "군위", "김천", "문경", "봉화", "상주", "성주", "안동", "영덕", "영양", "영주", "영천", "예천", "울릉", "울진", "의성", "청도", "청송", "칠곡", "포항"},
				{"경남 전체", "거제", "거창", "고성", "김해", "남해", "밀양", "사천", "산청", "양산", "의령", "진주", "창녕", "창원", "통영", "하동", "함안", "함양", "함천"},
				{"전북 전체", "고창", "군산", "김제", "남원", "무주", "부안", "순창", "완주", "익산", "임실", "장수", "전주", "정읍", "진안"},
				{"전남 전체", "강진", "고흥", "곡성", "광양", "구례", "나주", "담양", "목포", "무안", "보성", "순천", "신안", "여수", "영광", "영암", "완도", "장성", "장흥", "진도", "함평", "해남", "화순"},
				{"제주 전체", "서귀포시", "제주시"}
		};

		Map<String, String[]> regionMap = new HashMap<>();

		for (int i = 0; i < regName.length; i++) {
			regionMap.put(regName[i], region1[i]);
		}

		model.addAllAttributes(regionMap);
		
		return "html/hall/hall_list";	
	}
	
	@PostMapping("/list")
	public String FilterList(Model model) {
	
		
		return "html/hall/hall_list";
	}

	@RequestMapping(value = "/list/check", method = RequestMethod.POST)
	@ResponseBody
	public String testCheck(@RequestParam(value = "filterValueArr[]") List<String> valueArr) {
		System.out.println(valueArr.toString());
		String chk;
		if(valueArr.size() < 1) {
			chk = "비어있음";
		} else {
			chk = "정상값";			
		}
		return chk;
	}
	
	
}
