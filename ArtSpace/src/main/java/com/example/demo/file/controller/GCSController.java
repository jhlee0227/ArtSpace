package com.example.demo.file.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.file.dto.GCSRequest;
import com.example.demo.file.service.GCSService;


@Controller
public class GCSController {

	private final GCSService gcsService;
	
	public GCSController(GCSService gcsService) {
		this.gcsService = gcsService;
	}

    @PostMapping("/api/gcs/upload")
    public ResponseEntity<Void> objectUpload(GCSRequest gcsRequest) throws IOException {

        gcsService.uploadObject(gcsRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}