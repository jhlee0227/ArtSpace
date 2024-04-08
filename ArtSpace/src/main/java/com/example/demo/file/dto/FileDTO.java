package com.example.demo.file.dto;

public class FileDTO {
	// 홈페이지 파일에 대한 table
	
	private Integer file_id;		// 파일 ID
	private String path;			// 파일 주소
	private String org_file_name;		// 원래 파일명
	private String stored_file_name;	// 저장된 파일명
	
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOrg_file_name() {
		return org_file_name;
	}
	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}
	public String getStored_file_name() {
		return stored_file_name;
	}
	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}

	
}
