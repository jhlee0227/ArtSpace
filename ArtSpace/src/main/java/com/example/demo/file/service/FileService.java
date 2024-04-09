package com.example.demo.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.file.dto.GCSRequest;

public interface FileService {

	void uploadObject(MultipartFile[] files, Integer c_id);

	void uploadHallImage(MultipartFile[] files, Integer id);

}
