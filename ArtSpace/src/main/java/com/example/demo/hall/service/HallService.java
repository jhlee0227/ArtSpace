package com.example.demo.hall.service;

import java.util.List;

import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;

public interface HallService {

	public void insert(HallDTO hallDTO);

	public HallDTO findById(Integer id);

	public Integer findLastIndex();

	public void update(HallDTO hallDTO);

	public List<EquipmentDTO> getEquiList(Integer id);

	public void insertEqui(EquipmentDTO equiDTO, Integer id);

	public void cancelHall(Integer id);

	public void deleteEqui(Integer id);




}
