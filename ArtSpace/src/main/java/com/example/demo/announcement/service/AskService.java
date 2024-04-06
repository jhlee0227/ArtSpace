package com.example.demo.announcement.service;

import java.util.List;

import com.example.demo.announcement.dto.AskDto;


public interface AskService {

	void insert1(AskDto askDTO);

	List<AskDto> getAsk();

}

