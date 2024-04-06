package com.example.demo.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.file.dto.GCSRequest;
import com.example.demo.file.service.FileService;

@Controller
public class FileController {

	@Autowired
	FileService fileService;
	
	@PostMapping("/api/gcs/upload")
    public String objectUpload(GCSRequest gcsRequest) {
        fileService.uploadObject(gcsRequest);
		return "redirect:/company/info";
    }
}
