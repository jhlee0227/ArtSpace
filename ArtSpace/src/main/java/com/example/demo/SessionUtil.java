package com.example.demo;


import jakarta.servlet.http.HttpSession;

public class SessionUtil {	
	
	
	private Integer user_id; 
	private String authority;
	private String nickname;
	
	public void setSesstionValue(HttpSession session) {
		this.user_id = (Integer) session.getAttribute("user_id");
		this.authority = (String) session.getAttribute("authority");
		this.nickname = (String) session.getAttribute("nickname");
	}
	
	public Integer getUser_id() {
		return this.user_id;
	}
	
	public String getAuthority() {
		return this.authority;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	  
	  
}
