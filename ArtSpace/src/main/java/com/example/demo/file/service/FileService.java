package com.example.demo.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.file.dto.FileDTO;
import com.example.demo.file.dto.GCSRequest;
import com.google.cloud.storage.Blob;

public interface FileService {

	void uploadObject(GCSRequest gcsRequest);

	void insertHallImage(MultipartFile[] files, Integer id);

	public List<Blob> downloadImage(List<FileDTO> files);
}
