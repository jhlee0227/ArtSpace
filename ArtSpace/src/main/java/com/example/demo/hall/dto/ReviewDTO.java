package com.example.demo.hall.dto;

import java.time.LocalDateTime;

import com.example.demo.file.dto.FileDTO;

public class ReviewDTO {
	
	private Integer review_id;			// 리뷰ID PK
	private String review_content;		// 리뷰내용
	private Integer user_id;			// 유저ID FK
	private Integer hall_id;			// 공연장ID FK
	private Integer rating;				// 별점
	private LocalDateTime create_date;			// 리뷰작성일
	
	private String hall_name;			// 공연장명

	private String nickname;			// 유저닉네임
	
	private FileDTO mainImage;
	
	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getHall_id() {
		return hall_id;
	}

	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public LocalDateTime getCreate_date() {
		return create_date;
	}

	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}

	public String getHall_name() {
		return hall_name;
	}

	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public FileDTO getMainImage() {
		return mainImage;
	}

	public void setMainImage(FileDTO mainImage) {
		this.mainImage = mainImage;
	}

	
	
}
