package com.example.demo.company.dto;

public class CompanyFileDTO {

	private Integer c_file_id; // 법인파일ID PK
	private String file_name; // 서류이름
	private Integer file_id; // 파일ID FK
	private Integer company_id; // 법인ID FK

	private Integer user_id; // 유저ID

	public Integer getC_file_id() {
		return c_file_id;
	}

	public void setC_file_id(Integer c_file_id) {
		this.c_file_id = c_file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getFile_id() {
		return file_id;
	}

	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

}
