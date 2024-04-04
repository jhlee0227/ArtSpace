package com.example.demo.file.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GCSRequest {

    private String name;
    private MultipartFile file;

}
