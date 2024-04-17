package com.example.demo.hall.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.file.dao.FileDAO;
import com.example.demo.file.dao.HallFileDAO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dao.HallDAO;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallImageDTO;
import com.example.demo.hall.dto.HallTimeDTO;
import com.example.demo.mypage.dto.LikeDTO;


@Service
public class HallServiceImpl implements HallService{

	@Autowired
	HallDAO hallDAO;
	
	@Autowired
	FileDAO fileDAO;
	
	@Autowired
	HallFileDAO hallFileDAO;
	

	// 새로운 공연장 등록 폼
	@Override
	public HallDTO newHallform() {
		HallDTO hall = new HallDTO();

		List<HallTimeDTO> timeList = new LinkedList<>();
		HallTimeDTO time = new HallTimeDTO();
		String[] timeArr = {"오전", "오후", "저녁", "하루"};

		for (int i = 0; i < timeArr.length; i++) {
			time.setType(timeArr[i]);
			time.setPrice(0);
			time.setIscheck(false);
			timeList.add(time);			
		}
		hall.setHallTimeList(timeList);
		return hall;
	}


	// 예약 가능 시간 세팅
	@Override
	public List<HallTimeDTO> setHallTimeList(HallDTO hallInfo) {
		List<HallTimeDTO> timeList = new LinkedList<>();
		List<HallTimeDTO> hallTimes = hallDAO.getHallTimeList(hallInfo.getHall_id());
		String[] timeArr = {"오전", "오후", "저녁", "하루"};

		for (int i = 0; i < timeArr.length; i++) {
			HallTimeDTO time = new HallTimeDTO();
			time.setType(timeArr[i]);
			time.setPrice(0);
			time.setIscheck(false);

			for (int j = 0; j < hallTimes.size(); j++) {
				if(time.getType().equals(hallTimes.get(j).getType())) {
					time.setPrice(hallTimes.get(j).getPrice());					
					time.setIscheck(true);
					break;
				}
			}
			timeList.add(time);
		}
		return timeList;
	}
	
	// 공연장 기본 정보 insert
	@Override
	public void insert(HallDTO hallDTO) {
		hallDAO.insert(hallDTO);

		for (int i = 0; i < hallDTO.getHallTimeList().size(); i++) {
			if(hallDTO.getHallTimeList().get(i).getType() != null) {
				hallDTO.getHallTimeList().get(i).setHall_id(hallDTO.getHall_id());
				hallDTO.getHallTimeList().get(i).setIscheck(true);
				hallDAO.insertHallTime(hallDTO.getHallTimeList().get(i));				
			}
		}
		
	}

	// 공연장 기본정보 update
	@Override
	public void update(HallDTO hallDTO) {	
		hallDAO.deleteAllTime(hallDTO.getHall_id());
		
		for (int i = 0; i < hallDTO.getHallTimeList().size(); i++) {
			if(hallDTO.getHallTimeList().get(i).getType() != null) {
				hallDTO.getHallTimeList().get(i).setHall_id(hallDTO.getHall_id());
				hallDTO.getHallTimeList().get(i).setIscheck(true);
				hallDAO.insertHallTime(hallDTO.getHallTimeList().get(i));				
			}
		}
		hallDAO.update(hallDTO);
	}
	

	// 포스트 게시 On
	@Override
	public void visibilityTrue(Integer hall_id) {
		hallDAO.visibilityTrue(hall_id);
	}
	
	// 포스트 게시 Off
	@Override
	public void visibilityFalse(Integer hall_id) {
		hallDAO.visibilityFalse(hall_id);
	}

	
	
	// 해당 공연장의 장비 정보 저장(insert)하기
	@Override
	public void insertEqui(EquipmentDTO equiDTO, Integer id) {		
		equiDTO.setHall_id(id);
		hallDAO.insertEquiList(equiDTO);
	}


	// id로 해당 공연장 찾기
	@Override
	public HallDTO findById(Integer hall_id, Integer user_id) {
		HallDTO hallDTO = hallDAO.findById(hall_id);
		FileDTO mainImage = fileDAO.getHallMainFile(hallDTO.getHall_id());
		hallDTO.setMainImage(mainImage);
		
		// 좋아요
		LikeDTO like = new LikeDTO();
		like.setHall_id(hallDTO.getHall_id());
		like.setUser_id(user_id);			
		String status = hallDAO.getHallLikeStatus(like);

		if(status == null || status == "") {
			status = "N";
		}
		hallDTO.setLikeStatus(status);	
		
		return hallDTO;
	}
		

	// 해당 공연장의 장비 목록 다 가져오기
	@Override
	public List<EquipmentDTO> getEquiList(Integer id) {
		return hallDAO.getEquiList(id);
	}


	// 공연장 등록 취소해서 내용 전부 삭제
	@Override
	public void cancelHall(Integer id) {
		deleteEqui(id);
		deleteHallTime(id);
		deleteImages(id);
		hallDAO.deleteHall(id);
	}
	
	// 모든 장비 삭제
	@Override
	public void deleteEqui(Integer id) {
		hallDAO.deleteAllEquiList(id);
	}

	// 모든 예약 시간 세팅 삭제
	@Override
	public void deleteHallTime(Integer id) {
		hallDAO.deleteAllTime(id);
	}


	// 파일리스트 리턴함
	@Override
	public List<FileDTO> getImageList(Integer hall_id) {
		List<Integer> fileIDList = hallFileDAO.getImageList(hall_id);
		if(fileIDList.size() > 0 ) {
			List<FileDTO> files = fileDAO.getFileList(fileIDList);
			return files;
		}
		return null;			
	}


	// 정보 업데이트 전에 기존 사진 삭제
	@Override
	public void deleteImages(Integer hall_id, String[] deleteURL) {		
		List<Integer> curFileList = hallFileDAO.getImageList(hall_id);
		
		if(curFileList.size() > 0 ) {
			// 지울 URL로 파일DB에서 지울 파일 아이디 가져옴
			List<Integer> fileIDList = fileDAO.getFileIDList(deleteURL);
			
			if(fileIDList.size() > 0) {
				hallFileDAO.deleteImages(fileIDList);
				fileDAO.deleteFiles(fileIDList);				
			} else {
				// 주소가 잘못되었을 확률 99%
			}
		}
	}
	
	// 캔슬시 삭제하는 메소드
	@Override
	public void deleteImages(Integer hall_id) {		
		hallFileDAO.deleteAllImages(hall_id);					
		
		List<Integer> fileIDList = fileDAO.getHallImageFileIDList(hall_id);
		if(fileIDList.size() > 0) {
			hallFileDAO.deleteImages(fileIDList);
			fileDAO.deleteFiles(fileIDList);				
		}
	}


	// 좋아요
	@Override
	public void likeHall(Integer hall_id, Integer user_id, String status) {
		LikeDTO like = new LikeDTO();
		like.setHall_id(hall_id);
		like.setUser_id(user_id);
		like.setStatus(status);
		
		
		if(hallDAO.likeCheck(like) > 0) {
			hallDAO.updateLikeHall(like);
		} else {
			hallDAO.likeHall(like);			
		}
		
	}




}
