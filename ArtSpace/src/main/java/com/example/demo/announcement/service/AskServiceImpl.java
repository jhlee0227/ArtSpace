package com.example.demo.announcement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.announcement.dao.AskDao;
import com.example.demo.announcement.dto.AskDto;
@Service
public class AskServiceImpl implements AskService{

	@Autowired
	AskDao askDao;
	
	@Override
	public void insert(AskDto askDTO) {
		askDao.insert(askDTO);
		
	}

	@Override
	public List<AskDto> getAsk() {
		// TODO Auto-generated method stub
		return askDao.getAsk();
	}



}