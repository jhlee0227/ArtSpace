package com.example.demo.user.dto;

public class UserDTO {

	private Integer user_id;	// 유저 아이디 PK
	private String name;
	private String email;
	private String password;
	private String nickname;
	private String phone;
	private String authority;
	private Character leave_status;
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Character getLeave_status() {
		return leave_status;
	}
	public void setLeave_status(Character leave_status) {
		this.leave_status = leave_status;
	}
	
	
	
	
}
