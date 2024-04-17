package com.example.demo.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.SessionUtil;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReservationEquipmentDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;
import com.example.demo.reservation.service.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("reservation")
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	SessionUtil user_session = new SessionUtil();

	@Autowired
	HttpSession session;
	
	// 업데이트 처리
	@PostMapping("insert")
	@ResponseBody
	public String hall_Update(@RequestParam Map<String, Object> parameters) {		
		String reservationVal = parameters.get("reservation").toString();
		String timeListVal = parameters.get("timeList").toString();
		String equipListVal = parameters.get("equipList").toString();
		
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "login";
		}
		
		// 1. Data 확인 및 Validation 체크
        ObjectMapper mapper = new ObjectMapper();   // JSON을 Object화 하기 위한 Jackson ObjectMapper 이용
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	// DTO 필드에 없는 값은 무시
        ReservationDTO reservation = null;
        List<ReserveDateDTO> dateList = null;
        List<ReservationEquipmentDTO> equipList = null;
        
		try {
		    //변환된 데이터를 List형태에 저장
		    //JSON 파일을 Java 객체로 deserialization 하기 위해서 ObjectMapper의 readValue() 메서드를 이용
	        reservation = mapper.readValue(reservationVal, new TypeReference<ReservationDTO>(){});
	        dateList = mapper.readValue(timeListVal, new TypeReference<List<ReserveDateDTO>>(){});
	        equipList = mapper.readValue(equipListVal, new TypeReference<List<ReservationEquipmentDTO>>(){});	      	        
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reservation.setReserveDateList(dateList);
		reservation.setReservationEquipmentList(equipList);
		reservation.setUser_id(user_session.getUser_id());
		
		reservationService.insert(reservation);
		


		return "sucsses";
	}
}
