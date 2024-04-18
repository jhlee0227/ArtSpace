package com.example.demo.reservation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.EmailUtil;
import com.example.demo.SessionUtil;
import com.example.demo.email.EmailMessage;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReservationEquipmentDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;
import com.example.demo.reservation.service.ReservationService;
import com.example.demo.user.dto.UserDTO;
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
	
	@Autowired
	private EmailUtil emailUtil;

	// 업데이트 처리
	@PostMapping("insert")
	@ResponseBody
	public String hall_Update(@RequestParam Map<String, Object> parameters) {		
		String reservationVal = parameters.get("reservation").toString();
		String timeListVal = parameters.get("timeList").toString();
		String equipListVal = parameters.get("equipList").toString();
		
		// 로그인 안되어있으면 돌려보내기
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "login";
		} else {
			// 로그인 되어있는데 일반 유저가 아니면 리턴
			if(!user_session.getAuthority().equals("SU")) {
				return "일반 회원만 예약할 수 있습니다.";				
			}
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
		
		
		// 시간 중복 체크
		for (ReserveDateDTO date : dateList) {
			// 중복이면 돌려보내기
			if(reservationService.duplicationCheck(date) > 0) {
				return "이미 예약된 시간입니다. " + date.getReserve_date() + " : " + date.getReserve_time();
			}
			if(date.getReserve_time().equals("하루")) {
				if(reservationService.dayDuplicationCheck(date) > 0) {
					return "예약된 시간대가 중복되어 사용할 수 없습니다." + date.getReserve_date() + " : " + date.getReserve_time();
				}				
			}
		}
		
		
		// 나머지 필요 정보 넣고 insert
		reservation.setReserveDateList(dateList);
		reservation.setReservationEquipmentList(equipList);
		reservation.setUser_id(user_session.getUser_id());
		reservation.setCreate_date(LocalDateTime.now());
		
		reservationService.insert(reservation);
		
		UserDTO scUser = reservationService.getSCUser(reservation.getHall_id());
		sendreservationEmail(scUser, reservation.getReserve_id());
		
		return "success";
	}
	
	
	// 법인 유저 이메일로 예약완료 메일 보내기
	public void sendreservationEmail(UserDTO user, Integer reserve_id) {
		ReservationDTO reservation = reservationService.getReservation(reserve_id);
		
		// 예약완료 메세지 메일 보내기
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setTo(user.getEmail());
		emailUtil.sendReservationSCEmail(emailMessage, reservation);
		
	}
	
}
