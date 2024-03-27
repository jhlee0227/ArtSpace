package com.example.demo.mypage.dto;

public class PerformerDTO {
	
	private Integer perfo_id; 		// 공연자ID PK
	private String group_name;		// 단체명
	private String leader_name;		// 대표자명
	private Integer member_num;		// 인원수
	private String leader_phone;	// 대표번호
	private Integer perform_num;	// 공연횟수
	private String perfo_work;		// 공연한작품
	private Integer user_id;		// 유저ID FK
	
	public Integer getPerfo_id() {
		return perfo_id;
	}
	public void setPerfo_id(Integer perfo_id) {
		this.perfo_id = perfo_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getLeader_name() {
		return leader_name;
	}
	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}
	public Integer getMember_num() {
		return member_num;
	}
	public void setMember_num(Integer member_num) {
		this.member_num = member_num;
	}
	public String getLeader_phone() {
		return leader_phone;
	}
	public void setLeader_phone(String leader_phone) {
		this.leader_phone = leader_phone;
	}
	public Integer getPerform_num() {
		return perform_num;
	}
	public void setPerform_num(Integer perform_num) {
		this.perform_num = perform_num;
	}
	public String getPerfo_work() {
		return perfo_work;
	}
	public void setPerfo_work(String perfo_work) {
		this.perfo_work = perfo_work;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	

}
