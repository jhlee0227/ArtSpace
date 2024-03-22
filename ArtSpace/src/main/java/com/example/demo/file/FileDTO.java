package com.example.demo.file;

import lombok.Data;

@Data
public class FileDTO {
	// 홈페이지 파일에 대한 table
	
	private Integer file_id;		// 파일 ID
	private String path;			// 파일 주소
	private String org_file_name;		// 원래 파일명
	private String stored_file_name;	// 저장된 파일명
	
}
