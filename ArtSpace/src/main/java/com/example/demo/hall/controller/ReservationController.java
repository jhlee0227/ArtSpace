package com.example.demo.hall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.SessionUtil;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallFilterDTO;
import com.example.demo.hall.dto.ReservationDTO;
import com.example.demo.hall.dto.ReserveDateDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("reservation")
public class ReservationController {

	SessionUtil user_session = new SessionUtil();

	@Autowired
	HttpSession session;
	
	// 업데이트 처리
	@PostMapping("insert")
	@ResponseBody
	public String hall_Update(@RequestParam("reservation") ReservationDTO reservation) {				
		System.out.println("예약 자료~");
		
//		user_session.setSessionValue(session);
//		if (user_session.getUser_id() == null) {
//			return "login";
//		}
		
		System.out.println(reservation.getFood());


		return "sucsses";
	}
}
