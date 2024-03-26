package com.example.demo.hall.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;

@Repository
public class HallDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public void insert(HallDTO hallDTO) {
		sqlSession.insert("hall.insert", hallDTO);
	}

	public HallDTO findById(Integer id) {
		return sqlSession.selectOne("hall.findById", id);
	}

	public Integer findLastIndex() {
		return sqlSession.selectOne("hall.findLastIndex");
	}

	public void update(HallDTO hallDTO) {
		sqlSession.update("hall.update_info", hallDTO);
		
	}

	public List<EquipmentDTO> getEquiList(Integer id) {
		return sqlSession.selectList("hall.getEquiList", id);
	}

	public void deleteAllEquiList(Integer id) {
		sqlSession.delete("hall.equi_delete_all", id);
	}

	public void deleteHall(Integer id) {
		sqlSession.delete("hall.delete", id);		
	}

	
	public void insertEquiList(EquipmentDTO equiDTO) {
		sqlSession.insert("hall.equi_insert", equiDTO);
		
	}


}
