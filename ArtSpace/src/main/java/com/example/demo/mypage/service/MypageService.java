package com.example.demo.mypage.service;

import com.example.demo.user.dto.UserDTO;

public interface MypageService {

	public UserDTO findByID(Integer id);

	public void updateNickname(UserDTO dto, Integer id);

	public void updatePw(UserDTO dto, Integer id);
}
