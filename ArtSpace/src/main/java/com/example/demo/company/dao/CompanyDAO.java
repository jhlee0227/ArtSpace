package com.example.demo.company.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	public List<ReviewDTO> getReview() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("company.getReview");
	}
	
	
}
