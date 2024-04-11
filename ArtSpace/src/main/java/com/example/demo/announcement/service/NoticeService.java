package com.example.demo.announcement.service;

import java.util.List;

import com.example.demo.announcement.dto.NoticeDto;

public interface NoticeService {

	void insert(NoticeDto noticeDTO);

	List<NoticeDto> getNotice();

	void update(NoticeDto noticeDTO);

}
