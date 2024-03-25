package com.example.demo.mypage.service;

import com.example.demo.mypage.dto.MypageDTO;

public interface MypageService {

	public MypageDTO findByID(Integer id);

	public void updateNickname(MypageDTO dto);
}
