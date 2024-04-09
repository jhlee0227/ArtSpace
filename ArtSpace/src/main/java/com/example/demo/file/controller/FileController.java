package com.example.demo.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.company.service.CompanyService;
import com.example.demo.file.dto.GCSRequest;
import com.example.demo.file.service.FileService;

@Controller
public class FileController {

	@Autowired
	FileService fileService;


	// 파일 갯수만큼 GCS와 DB에 저장
	@PostMapping("/api/gcs/upload")
	public String objectUpload(@RequestParam("file") MultipartFile[] files,
			@RequestParam("company_id") Integer company_id) {

		fileService.uploadObject(files, company_id);
		return "redirect:/company/info";
	}

	// 단일 파일 GCS와 DB에 저장
//	 @PostMapping("/api/gcs/upload")
//	    public String objectUpload(GCSRequest gcsRequest) {
//	        fileService.uploadObject(gcsRequest);
//			return "redirect:/company/info";
//	    }
}
