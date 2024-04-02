package com.example.demo.announcement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.announcement.dao.BoardDao;
import com.example.demo.announcement.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	
	@Override
	public void insert(BoardDto boardDTO) {
		
		boardDao.insert(boardDTO);
	}

	@Override
	public List<BoardDto> getNotice() {
		// TODO Auto-generated method stub
		return boardDao.getNotice();
	}

}
