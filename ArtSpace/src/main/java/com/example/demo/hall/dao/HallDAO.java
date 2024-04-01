package com.example.demo.hall.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallTimeDTO;

@Repository
public class HallDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 공연장 기본정보 insert
	public void insert(HallDTO hallDTO) {
		sqlSession.insert("hall.insert", hallDTO);
	}
	
	// 공연장 시간 정보 insert
	public void insertHallTime(HallTimeDTO hallTimes) {
		sqlSession.insert("hall.insert_time", hallTimes);		
	}
	
	// 공연장 장비 정보 insert
	public void insertEquiList(EquipmentDTO equiDTO) {
		sqlSession.insert("hall.equi_insert", equiDTO);
	}
	
	// 공연장 아이디로 해당 공연장 정보 찾아옴
	public HallDTO findById(Integer id) {
		return sqlSession.selectOne("hall.findById", id);
	}

	// 마지막 공연장 index 찾아옴
	public Integer findLastIndex() {
		return sqlSession.selectOne("hall.findLastIndex");
	}

	// 공연장 기본 정보 update
	public void update(HallDTO hallDTO) {
		sqlSession.update("hall.update_info", hallDTO);
	}
	
	// 공연장 시간 정보 update
	public void updateHallTime(HallTimeDTO hallTimes) {
		sqlSession.update("hall.update_hall_time",  hallTimes);
	}

	// 공연장 리스트 가져옴(정렬 기준)
	public List<HallDTO> getHallList(String sort) {
		return sqlSession.selectList("hall.getHallList", sort);
	}

	// 공연장 시간 정보 리스트를 가져옴
	public List<HallTimeDTO> getHallTimeList(Integer id) {
		return sqlSession.selectList("hall.getHallTimeList", id);
	}
	
	// 장비 정보 리스트 가져옴
	public List<EquipmentDTO> getEquiList(Integer id) {
		return sqlSession.selectList("hall.getEquiList", id);
	}

	// 모든 장비 정보를 삭제함(form 입력받는 구간에서만 사용함)
	public void deleteAllEquiList(Integer id) {
		sqlSession.delete("hall.equi_delete_all", id);
	}

	// 공연장 기본 정보를 삭제함 (form에서 등록 취소할 때만 사용함)
	public void deleteHall(Integer id) {
		sqlSession.delete("hall.delete", id);		
	}
	
	public void deleteAllTime(Integer id) {
		sqlSession.delete("hall.deleteAllTime", id);
	}


	
	
	//	========================================
	// -- hall list 작업 --

	

}
