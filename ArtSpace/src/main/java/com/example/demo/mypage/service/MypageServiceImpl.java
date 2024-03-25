package com.example.demo.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.mypage.dao.MypageDAO;
import com.example.demo.mypage.dto.MypageDTO;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageDAO mypageDAO;
	

	@Override
	public MypageDTO findByID(Integer id) {
		// TODO Auto-generated method stub
			return mypageDAO.findById(id);
	}

	@Override
	public void updateNickname(MypageDTO dto) {
		mypageDAO.updateNickname(dto);
		
	}

	
}
