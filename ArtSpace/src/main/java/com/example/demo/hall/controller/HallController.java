package com.example.demo.hall.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.SessionUtil;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.file.dto.GCSRequest;
import com.example.demo.file.service.FileService;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallFilterDTO;
import com.example.demo.hall.dto.HallImageDTO;
import com.example.demo.hall.service.HallService;
import com.example.demo.mypage.dto.LikeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.storage.model.StorageObject;
import com.google.cloud.storage.Blob;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("hall")
public class HallController {

	@Autowired
	private HallService hallService;

	@Autowired
	FileService fileService;

	SessionUtil user_session = new SessionUtil();

	@Autowired
	HttpSession session;

	// 기본 공연장 정보 입력 화면 띄우기
	@GetMapping("form")
	public String showForm(Model model) {
		user_session.setSessionValue(session);

		// 로그인 권한 체크
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		} else if (!user_session.getAuthority().equals("SCY")) {
			// 법인 이용자인지 권한 체크
			// return "redirect:/";
		} else {
			// 승인된 법인이면
		}

		// 초기화
		HallDTO hall = hallService.newHallform();

		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());

		model.addAttribute("hall_info", hall);
		model.addAttribute("action", "/hall/form/insert");
		return "html/hall/hall_form";
	}

	// 이전 버튼을 눌렀을때 공연장 정보 수정 화면 띄우기
	@GetMapping("form/{id}")
	public String shwForm(Model model, @PathVariable("id") Integer id, HttpServletResponse response) {
		user_session.setSessionValue(session);
		

		// 권한 체크
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}		
//		 else if(!user_session.getUser_id().equals(hallInfo.getUser_id())){
//			 return "redirect:/"; 
//		 } if(!user_session.getAuthority().equals("SCY")){
//			 return "redirect:/"; 
//		 }

		// 공연장 기본 정보들 불러오기
		HallDTO hallInfo = hallService.findById(id, user_session.getUser_id());
		hallInfo.setHallTimeList(hallService.setHallTimeList(hallInfo));
		List<FileDTO> imageList = hallService.getImageList(id);
		model.addAttribute("images", imageList);
		model.addAttribute("hall_info", hallInfo);

		model.addAttribute("user_id", user_session.getUser_id());
		model.addAttribute("nickname", user_session.getNickname());
		model.addAttribute("action", "/hall/form/update/" + id);

		return "html/hall/hall_form";
	}
	
	

	// insert 처리
	@PostMapping("/form/insert")
	@ResponseBody
	public String hallCreate(@ModelAttribute HallDTO hallDTO,
			@RequestParam("files") MultipartFile[] files) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		
		hallDTO.setCreate_date(LocalDateTime.now());
		hallDTO.setUser_id(user_session.getUser_id());

		hallService.insert(hallDTO);

		if (files != null) {
			fileService.insertHallImage(files, hallDTO.getHall_id());
		}
		//http://localhost:1105/hall/form/equipment/20
		return "/hall/form/equipment/" + hallDTO.getHall_id();
	}
	
	

	// 업데이트 처리
	@PostMapping("/form/update/{id}")
	@ResponseBody
	public String hall_Update(@ModelAttribute HallDTO hallDTO,
								@RequestParam("files") MultipartFile[] files,
								@PathVariable("id") Integer id) {		

		
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		if (files != null) {
			if(hallDTO.getDeleteImgList() != null) {
				hallService.deleteImages(id, hallDTO.getDeleteImgList());				
			}
			fileService.insertHallImage(files, id);
		}

		hallDTO.setHall_id(id);
		hallDTO.setUser_id(user_session.getUser_id());
		hallService.update(hallDTO);

		return "/hall/form/equipment/" + id;
	}

	// 장비 화면 띄우기
	@PostMapping("form/equipment/{id}")
	public String showFormEquipment(@PathVariable("id") Integer id, Model model) {
		user_session.setSessionValue(session);

		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("user_id", user_session.getUser_id());
			model.addAttribute("nickname", user_session.getNickname());
		}

		List<EquipmentDTO> equiList = hallService.getEquiList(id);
		model.addAttribute("equiList", equiList);

		model.addAttribute("hall_id", id);
		return "html/hall/hall_form_equipment";
	}

	// 이전 누르면 장비 저장하고 이전 화면으로 연결
	@PostMapping("form/equipment/insert/{id}")
	public String insertEquipment(@Valid @ModelAttribute HallDTO hallDTO, @PathVariable("id") Integer id) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}

		hallService.deleteEqui(id); // 전부 삭제하고
		List<EquipmentDTO> equiList = hallDTO.getEquiList();

		// 값이 모두 채워진 것만 리스트에 추가하기.
		for (int i = 0; i < hallDTO.getEquiList().size(); i++) {
			if (equiList.get(i).getEquip_name() != null
					&& !equiList.get(i).getEquip_type().equals("none")
					&& equiList.get(i).getEquip_num() != null
					&& equiList.get(i).getEquip_price() != null) {
				hallService.insertEqui(equiList.get(i), id);
			}
		}
		return "redirect:/hall/form/" + id;
	}

	// 등록 누르면 장비 저장하고 my페이지로 연결
	@PostMapping("form/complete/{id}")
	public String completeInsertHall(@Valid @ModelAttribute HallDTO hallDTO, @PathVariable("id") Integer id) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}

		hallService.deleteEqui(id); // 전부 삭제하고
		List<EquipmentDTO> equiList = hallDTO.getEquiList();

		// 값이 모두 채워진 것만 리스트에 추가하기.
		for (int i = 0; i < hallDTO.getEquiList().size(); i++) {
			if (equiList.get(i).getEquip_name() != null
					&& !equiList.get(i).getEquip_type().equals("none")
					&& equiList.get(i).getEquip_num() != null
					&& equiList.get(i).getEquip_price() != null) {
				hallService.insertEqui(equiList.get(i), id);
			}
		}
		
		hallService.visibilityTrue(id);
		return "redirect:/hall/detail/" + id;
	}

	// 등록 취소 버튼 누를 때
	@PostMapping("form/cancel/{id}")
	public String cancelHall(@PathVariable("id") Integer id) {
		user_session.setSessionValue(session);
		if (user_session.getUser_id() == null) {
			return "redirect:/login";
		}
		
		HallDTO hallInfo = hallService.findById(id, user_session.getUser_id());
		if(hallInfo.getVisibility().equals("Y")) {
			return "redirect:/company/hall";
		} else {			
			hallService.cancelHall(id);
		}
		
		
		return "redirect:/";
	}

	
	
	
	
	// 좋아요, 찜, 즐겨찾기
	@GetMapping("like/{id}")
	@ResponseBody
	public String showForm(@PathVariable("id") Integer hall_id, @RequestParam("status") String status) {
		user_session.setSessionValue(session);

		// 로그인 권한 체크
		if (user_session.getUser_id() == null) {
			return "login";
		}
		hallService.likeHall(hall_id, user_session.getUser_id(), status);
		
		return "success";
	}


}
