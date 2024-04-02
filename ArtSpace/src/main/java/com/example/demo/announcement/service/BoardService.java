package com.example.demo.announcement.service;

import java.util.List;

import com.example.demo.announcement.dto.BoardDto;

public interface BoardService {

	void insert(BoardDto boardDTO);

	List<BoardDto> getNotice();

}
