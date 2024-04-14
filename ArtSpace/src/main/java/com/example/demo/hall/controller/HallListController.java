package com.example.demo.hall.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.SessionUtil;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallFilterDTO;
import com.example.demo.hall.service.HallListService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mysql.cj.log.Log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HallListController {

	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HallListService hallListService;

	// 리스트 화면으로 넘어왔을 때(+검색)
	@GetMapping("/list")
	public String showHallList(Model model, HttpServletRequest request) {
		user_session.setSessionValue(session);
		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());

		List<HallDTO> hallList = null;			
		// 검색어가 들어왔을 때 검색어 + 기본 최신순 정렬
		if(request.getParameter("content") != null) {
			HallFilterDTO filter = new HallFilterDTO();
			filter.setContent(request.getParameter("content"));
			filter.setSort("create_date");
			hallList = hallListService.getFilterData(filter);

			// 헤더 검색창에 검색어 남겨두기
			model.addAttribute("content", request.getParameter("content"));
		} else {
			// 검색하지 않고 그냥 리스트 화면으로 넘어왔을 때 그냥 기본 리스트 불러옴
			hallList = hallListService.getList();
		}		
		model.addAttribute("hallList", hallList);
	
		Map<String, List<String>> regionMap = readCsvRegion();
		model.addAllAttributes(regionMap);
		
		return "html/hall/hall_list";	
	}

	// 필터 & 정렬
	@RequestMapping(value = "/list/check", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> testCheck(@RequestBody HallFilterDTO filter){	
		List<HallDTO> hallList = hallListService.getFilterData(filter);
		return new ResponseEntity<List<HallDTO>>(hallList, HttpStatus.OK);
	}	
	
	
	// 지역 목록 CSV파일에서 불러오기
	public Map<String, List<String>> readCsvRegion(){
		// 추후 return 할 데이터 목록
		Map<String, List<String>> map = new HashMap<String, List<String>>();
			    
	    try{
	    	BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/static/csv/region.csv"));
	        String line = "";

	        while((line = br.readLine()) != null){
				List<String> stringList = new ArrayList<String>();
				String stringArray[] = line.split(",");	// 한줄 나오기				 				
				stringList = Arrays.asList(stringArray);
	            				
				List<String> strList = new ArrayList<String>();	// 지역 value값들 담을 것
				
 				// header 컬럼 개수를 기준으로 데이터 set
				for(int i = 1; i < stringList.size(); i++){
					if(stringList.get(i).trim() != "") {							
						strList.add(stringList.get(i));
					}
				}
				// 0번째가 키값
				map.put(stringList.get(0), strList);
	        }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }

	    return map;
	}
	
	
	
	
}
