package com.example.demo.hall.service;

import java.util.List;

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
		hallDAO.insert(hallDTO);
	}

	// 공연장 기본정보 update
	@Override
	public void update(HallDTO hallDTO) {
		hallDAO.update(hallDTO);
	}

	// id로 해당 공연장 찾기
	@Override
	public HallDTO findById(Integer id) {
		return hallDAO.findById(id);
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
	public void insertEqui(List<EquipmentDTO> equiDTOList, Integer id) {		
		for (EquipmentDTO equipmentDTO : equiDTOList) {			
			equipmentDTO.setHallId(id);
			hallDAO.insertEquiList(equipmentDTO);
		}
	}
	
}
