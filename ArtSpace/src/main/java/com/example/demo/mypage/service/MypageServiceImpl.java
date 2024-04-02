package com.example.demo.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.mypage.dao.MypageDAO;
import com.example.demo.mypage.dto.LikeDTO;
import com.example.demo.mypage.dto.PerformerDTO;
import com.example.demo.user.dto.UserDTO;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageDAO mypageDAO;
	

	@Override
	public UserDTO findByID(Integer id) {
		// TODO Auto-generated method stub
			return mypageDAO.findById(id);
	}

	@Override
	public void updateNickname(UserDTO dto) {
		mypageDAO.updateNickname(dto);
		
	}

	@Override
	public void updatePw(UserDTO dto) {
		mypageDAO.updatePw(dto);
	}

	@Override
	public PerformerDTO findByPID(Integer id) {
		
		return mypageDAO.findByPID(id);
	}

	@Override
	public void insert(PerformerDTO dto) {
		
		PerformerDTO existPerformer = mypageDAO.findByPID(dto.getUser_id());
		if (existPerformer == null) {
			mypageDAO.insertPerformer(dto);
		} else {
			mypageDAO.updatePerformer(dto);
		}
	}

	@Override
	public void leave(UserDTO dto) {
		mypageDAO.leave(dto);
	}

	@Override
	public List<HallDTO> getAllLike(Integer id) {
		return mypageDAO.getAllLike(id);
	}

	@Override
	public void likeDelete(LikeDTO like) {
		mypageDAO.likeDelete(like);
		
	}

	@Override
	public LikeDTO getLikeInfo(Integer id) {
		return mypageDAO.getLikeInfo(id);
	}



	
}
