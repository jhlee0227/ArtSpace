package com.example.demo.hall.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.SessionUtil;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallAnswerDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallQuestionDTO;
import com.example.demo.hall.service.DetailService;
import com.example.demo.hall.service.HallService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("hall")
public class DetailController {
	
	SessionUtil user_session = new SessionUtil();
	
	@Autowired
	DetailService detailService;
	
	@Autowired
	HallService hallService;
	
	@Autowired
	HttpSession session;
	
	// 임시
	@GetMapping("detail/{id}")
	public String detailPage(Model model, @PathVariable("id") Integer id) {
		user_session.setSessionValue(session);

		if (user_session.getUser_id() != null) {
			model.addAttribute("user_id", user_session.getUser_id());
			model.addAttribute("nickname", user_session.getNickname());
			model.addAttribute("authority", user_session.getAuthority());
		}
		
		// 공연장 기본 정보들 불러오기
		HallDTO hall = hallService.findById(id, user_session.getUser_id());
		hall.setHallTimeList(hallService.setHallTimeList(hall));
		hall.setEquiList(hallService.getEquiList(id));
		hall.setQuestionList(hallService.getQuestionList(id));
		
		List<FileDTO> images = hallService.getImageList(id);
		List<EquipmentDTO> equipList = hall.getEquiList();		
		

		int ft = (int) (hall.getArea() / 3.306);
		
		model.addAttribute("ft", ft);
		model.addAttribute("images", images);
		model.addAttribute("equipMap", getEquipMap(equipList));
		model.addAttribute("hall", hall);		

		return "html/hall/detail_page";
	}
	
	
	// 장비 리스트 쪼개기
	Map<String, List<EquipmentDTO>> getEquipMap(List<EquipmentDTO> equipList){
		List<EquipmentDTO> micList = new ArrayList<EquipmentDTO>();
		List<EquipmentDTO> speakerList = new ArrayList<EquipmentDTO>();
		List<EquipmentDTO> lightingList = new ArrayList<EquipmentDTO>();
		List<EquipmentDTO> stage_equipList = new ArrayList<EquipmentDTO>();
		List<EquipmentDTO> videoList = new ArrayList<EquipmentDTO>();
		List<EquipmentDTO> etcList = new ArrayList<EquipmentDTO>();
		
		for (EquipmentDTO equip : equipList) {
			switch (equip.getEquip_type()) {
				
				case "mic" : 
					micList.add(equip);
					break;
				case "speaker":
					speakerList.add(equip);
					break;
				case "Lighting":
					lightingList.add(equip);
					break;
				case "stage_equip":
					stage_equipList.add(equip);
					break;
				case "video":
					videoList.add(equip);
					break;
				case "etc":
					etcList.add(equip);
					break;
			}
		}
		
		Map<String, List<EquipmentDTO>> equipMap = new LinkedHashMap<String, List<EquipmentDTO>>();

		if(micList.size() > 0) {
			equipMap.put("마이크", micList);			
		}
		if(speakerList.size() > 0) {
			equipMap.put("스피커", speakerList);						
		}
		if(lightingList.size() > 0) {
			equipMap.put("조명", lightingList);
		}
		if(stage_equipList.size() > 0) {
			equipMap.put("무대장치", stage_equipList);
		}
		if(videoList.size() > 0) {
			equipMap.put("영상", videoList);
		}
		if(etcList.size() > 0) {
			equipMap.put("기타", etcList);
		}
		
		return equipMap;
	}
	
	
	// 공연장 질문
	@PostMapping("/question/insert")
	@ResponseBody
	public String questionInsert(@RequestParam("content") String content, @RequestParam("hall_id") Integer hall_id) {	
		if(user_session.getUser_id() == null) {
			return "login";
		}
		
		HallQuestionDTO question = new HallQuestionDTO();
		question.setContent(content);
		question.setHall_id(hall_id);
		question.setUser_id(user_session.getUser_id());
		question.setCreate_date(LocalDateTime.now());

		detailService.insertQuestion(question);
		
		return "success";
	}
	
	@PostMapping("question/delete")
	@ResponseBody
	public void questionDelete(@RequestParam("question_id") Integer question_id) {
		detailService.deleteQuestion(question_id);
	}

	@PostMapping("question/modify")
	@ResponseBody
	public String questionModify(@RequestParam("question_id") Integer question_id, @RequestParam("content") String content) {
		if(user_session.getUser_id() == null) {
			return "login";
		}
		
		HallQuestionDTO question = new HallQuestionDTO();
		question.setContent(content);
		question.setQuestion_id(question_id);
		
		detailService.modifyQuestion(question);
		return content;
	}

	
	// 공연장 질문 답변
	@PostMapping("/question/answer/insert")
	@ResponseBody
	public String answerInsert(@RequestParam("content") String content, @RequestParam("question_id") Integer question_id) {	
		if(user_session.getUser_id() == null) {
			return "login";
		}
		
		HallAnswerDTO answer = new HallAnswerDTO();
		answer.setQuestion_id(question_id);
		answer.setContent(content);
		answer.setUser_id(user_session.getUser_id());
		answer.setCreate_date(LocalDateTime.now());

		detailService.insertAnswer(answer);
		
		return "success";
	}
	
	
	

}
