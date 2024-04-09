package com.example.demo.company.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReservationDTO;
import com.example.demo.hall.dto.ReviewDTO;

@Repository
public class CompanyDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	public List<HallDTO> getHall(Integer user_id) {
		
		return sqlSession.selectList("company.getHall", user_id);
	}

	public List<ReservationDTO> getReserve(Integer user_id) {

		return sqlSession.selectList("company.getReserve", user_id);
	}

	public void reserveDelete(Integer reserve_id) {
		
		sqlSession.update("company.reserveDelete", reserve_id);
	}

	public void updateInfo(CompanyDTO dto) {
		
		sqlSession.update("company.updateInfo", dto);
	}

	public CompanyDTO findByID(Integer user_id) {
		
		return sqlSession.selectOne("company.info", user_id);
	}

	public void hallDelete(Integer hall_id) {
		sqlSession.update("company.hallDelete", hall_id);
	}

	public int fileCount(Integer company_id) {
		
		return sqlSession.selectOne("company.fileCount", company_id);
	}
	
	
}
