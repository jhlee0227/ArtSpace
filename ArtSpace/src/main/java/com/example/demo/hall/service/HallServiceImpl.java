package com.example.demo.hall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.UUIDgeneration;
import com.example.demo.hall.dao.HallDAO;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;


@Service
public class HallServiceImpl implements HallService{

	@Autowired
	HallDAO hallDAO;
	
	// 공연장 기본 정보 insert
	@Override
	public void insert(HallDTO hallDTO) {
		
		if(hallDTO.getMorning() == null) {
			hallDTO.setMorning(0);
		}
		if(hallDTO.getAfter() == null) {
			hallDTO.setAfter(0);
		}
		if(hallDTO.getEve() == null) {
			hallDTO.setEve(0);
		}		
		if(hallDTO.getFull() == null) {
			hallDTO.setFull(0);
		}
		
		hallDAO.insert(hallDTO);
	}

	// 공연장 기본정보 update
	@Override
	public void update(HallDTO hallDTO) {
		if(hallDTO.getMorning() == null) {
			hallDTO.setMorning(0);
		} else {
			hallDTO.setMorning(1);			
		}
		
		if(hallDTO.getAfter() == null) {
			hallDTO.setAfter(0);
		} else {
			hallDTO.setAfter(1);			
		}
		
		if(hallDTO.getEve() == null) {
			hallDTO.setEve(0);
		}else {
			hallDTO.setEve(1);			
		}
		
		if(hallDTO.getFull() == null) {
			hallDTO.setFull(0);
		} else {
			hallDTO.setFull(1);			
		}

		hallDAO.update(hallDTO);
	}

	// id로 해당 공연장 찾기
	@Override
	public HallDTO findById(Integer id) {
		return hallDAO.findById(id);
	}
	
	// 해당 공연장의 예약가능 시간 찾기
	@Override
	public HallDTO findHallTime(HallDTO hallDTO) {
		List<Map<String, Integer>> time = hallDAO.findHallTime(hallDTO.getHall_id());

		if(time.get(0).get("morning") == null) {
			hallDTO.setMorning(1);				
		} else {
			hallDTO.setMorning(time.get(0).get("morning"));			
		}

		if(time.get(0).get("after") == null) {
			hallDTO.setAfter(1);				
		} else {
			hallDTO.setAfter(time.get(0).get("after"));
		}

		if(time.get(0).get("eve") == null) {
			hallDTO.setEve(1);
		} else {
			hallDTO.setEve(time.get(0).get("eve"));
		}

		if(time.get(0).get("full") == null) {
			hallDTO.setFull(1);
		} else {
			hallDTO.setFull(time.get(0).get("full"));
		}
	
		return hallDTO;
	}
	

	// 마지막 index 찾기
	@Override
	public Integer findLastIndex() {
		return hallDAO.findLastIndex();
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
		hallDAO.deleteHall(id);
	}

	@Override
	public void deleteEqui(Integer id) {
		hallDAO.deleteAllEquiList(id);
	}


	
}
