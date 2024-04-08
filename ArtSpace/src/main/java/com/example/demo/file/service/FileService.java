package com.example.demo.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.file.dto.GCSRequest;

public interface FileService {

	void uploadObject(GCSRequest gcsRequest);

	void uploadHallImage(MultipartFile[] files, Integer id);

}
