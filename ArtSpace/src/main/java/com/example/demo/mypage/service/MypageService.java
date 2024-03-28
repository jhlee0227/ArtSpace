package com.example.demo.mypage.service;

import java.util.List;

import com.example.demo.mypage.dto.LikeDTO;
import com.example.demo.mypage.dto.PerformerDTO;
import com.example.demo.user.dto.UserDTO;

public interface MypageService {

	public UserDTO findByID(Integer id);
	
	public PerformerDTO findByPID(Integer id);

	public void updateNickname(UserDTO dto);

	public void updatePw(UserDTO dto);

	public void insert(PerformerDTO dto);

	public void leave(UserDTO dto);

}
