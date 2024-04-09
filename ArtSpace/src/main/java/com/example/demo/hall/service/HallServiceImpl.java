package com.example.demo.hall.service;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


@Service
public class HallServiceImpl implements HallService{

	@Autowired
	HallDAO hallDAO;
	
	@Autowired
	FileDAO fileDAO;
	
	@Autowired
	HallFileDAO hallFileDAO;
	

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

	// id로 해당 공연장 찾기
	@Override
	public HallDTO findById(Integer id) {
		HallDTO hallDTO = hallDAO.findById(id);
				
		return hallDTO;
	}
		

	// 해당 공연장의 장비 목록 다 가져오기
	@Override
	public List<EquipmentDTO> getEquiList(Integer id) {
		return hallDAO.getEquiList(id);
	}

	// 해당 공연장의 장비 정보 저장(insert)하기
	@Override
	public void insertEqui(EquipmentDTO equiDTO, Integer id) {		
		equiDTO.setHall_id(id);
		hallDAO.insertEquiList(equiDTO);
	}

	// 공연장 등록 취소해서 내용 전부 삭제
	@Override
	public void cancelHall(Integer id) {
		deleteEqui(id);
		deleteHallTime(id);
		hallDAO.deleteHall(id);
	}

	@Override
	public void deleteEqui(Integer id) {
		hallDAO.deleteAllEquiList(id);
	}

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


	// 정보 업데이트 전에 기존 사진 모두 삭제
	@Override
	public void deleteImages(Integer hall_id) {
		List<Integer> fileIDList = hallFileDAO.getImageList(hall_id);
		if(fileIDList.size() > 0 ) {
			hallFileDAO.deleteImages(hall_id);
			fileDAO.deleteFiles(fileIDList);
		}		
		
		
	}


}
