package com.example.demo.file.service;

import com.example.demo.file.dto.GCSRequest;

public interface FileService {

	void uploadObject(GCSRequest gcsRequest);
}
