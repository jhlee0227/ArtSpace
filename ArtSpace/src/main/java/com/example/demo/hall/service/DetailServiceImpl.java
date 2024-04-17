package com.example.demo.hall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hall.dao.DetailDAO;
import com.example.demo.hall.dao.HallDAO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.mypage.dto.LikeDTO;

@Service
public class DetailServiceImpl implements DetailService {
	
	@Autowired
	DetailDAO detailDAO;
	@Autowired
	HallDAO hallDAO;
	
	@Override
	public HallDTO findById(Integer id, Integer user_id) {
		HallDTO hall = detailDAO.findById(id);
		// 좋아요
		LikeDTO like = new LikeDTO();
		like.setHall_id(hall.getHall_id());
		like.setUser_id(user_id);			
		String status = hallDAO.getHallLikeStatus(like);

		if(status == null || status == "") {
			status = "N";
		}
		hall.setLikeStatus(status);	
		
		return hall;
		
	}

}
