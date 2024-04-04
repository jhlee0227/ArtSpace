package com.example.demo.announcement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.announcement.dao.NoticeDao;
import com.example.demo.announcement.dto.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	NoticeDao noticeDao;
	
	@Override
	public void insert(NoticeDto noticeDTO) {
		
		noticeDao.insert(noticeDTO);
	}

	@Override
	public List<NoticeDto> getNotice() {
		// TODO Auto-generated method stub
		return noticeDao.getNotice();
	}

}
