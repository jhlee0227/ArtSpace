package com.example.demo.hall.service;

import java.io.File;
import java.util.List;

import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallTimeDTO;

public interface HallService {

	public HallDTO newHallform();

	// Insert 관련
	public void insert(HallDTO hallDTO);

	public void insertEqui(EquipmentDTO equiDTO, Integer id);

	// UPDATE 관련
	public void update(HallDTO hallDTO);
	
	// SELECT 관련
	public HallDTO findById(Integer id);

	public List<HallTimeDTO> setHallTimeList(HallDTO hallInfo);

	public List<EquipmentDTO> getEquiList(Integer id);


	// DELETE 관련
	public void cancelHall(Integer id);

	public void deleteEqui(Integer id);

	public void deleteHallTime(Integer id);

	public List<FileDTO> getImageList(Integer id);

	public void deleteImages(Integer id, String[] deleteURL);







}
