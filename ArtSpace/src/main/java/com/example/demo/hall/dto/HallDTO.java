package com.example.demo.hall.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.file.dto.FileDTO;



public class HallDTO {

	private Integer hall_id;		// 공연장ID PK
	private String hall_name;		// 공연장명
	private Integer zip_code;		// 우편번호
	private String address1;		// 주소
	private String address2;		// 상세주소
	private String hall_description;	// 공연장설명
	private String warning;			// 주의 사항
	private Integer area;		// 면적
	private Integer width;		// 가로
	private Integer length;		// 세로
	private Integer height;		// 높이
	private Integer maximum;	// 최대수용인원수
	private Integer views;		// 조회수
	private LocalDateTime create_date;		// 등록 날짜
	private Integer user_id;		// 유저ID FK
	private String name;			// 유저이름
	private String visibility;		// 공연장 삭제상태
	
	
	private FileDTO mainImage;		// 대표 이미지 파일 정보
	private List<FileDTO> hallImageList;	// 실제 내 공연장 이미지 파일 정보들(상세 페이지용)
	
	private List<HallImageDTO> imageList;	// 
	private List<EquipmentDTO> equiList;	// 장비DTO리스트
	private List<HallTimeDTO> hallTimeList;	// 공연장시간DTO리스트
	private List<ReviewDTO> reviewList;		// 리뷰 리스트
	private List<HallQuestionDTO> questionList;	// 질문 리스트
	private String[] deleteImgList; // 삭제할 이미지URL List
	
	
	private Integer minPrice; // 내가가진 시간별 가격대의 최솟값
	private Integer likeNum;	// 해당 공연장을 찜한 수
	private Float rating;		// 해당 공연장 총 평균 평점
	
	private String likeStatus;	// 유저가 해당 공연장 찜했는지 상태(Y/N)
	

	
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public String getHall_name() {
		return hall_name;
	}
	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}
	public Integer getZip_code() {
		return zip_code;
	}
	public void setZip_code(Integer zip_code) {
		this.zip_code = zip_code;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getHall_description() {
		return hall_description;
	}
	public void setHall_description(String hall_description) {
		this.hall_description = hall_description;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getMaximum() {
		return maximum;
	}
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public LocalDateTime getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDateTime localDateTime) {
		this.create_date = localDateTime;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public List<EquipmentDTO> getEquiList() {
		return equiList;
	}
	public void setEquiList(List<EquipmentDTO> equiList) {
		this.equiList = equiList;
	}
	public List<HallTimeDTO> getHallTimeList() {
		return hallTimeList;
	}
	public void setHallTimeList(List<HallTimeDTO> hallTimeList) {
		this.hallTimeList = hallTimeList;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
  
	public String getVisibility() {
		return visibility;
	}
  
	public void setVisibility(String visibility) {
		this.visibility = visibility;
  }
  
	public List<HallImageDTO> getImageList() {
		return imageList;
	}
    
	public void setImageList(List<HallImageDTO> imageList) {
		this.imageList = imageList;
	}
	public String[] getDeleteImgList() {
		return deleteImgList;
	}
	public void setDeleteImgList(String[] deleteImgList) {
		this.deleteImgList = deleteImgList;
	}
	public FileDTO getMainImage() {
		return mainImage;
	}
	public void setMainImage(FileDTO mainImage) {
		this.mainImage = mainImage;
	}
	public List<FileDTO> getHallImageList() {
		return hallImageList;
	}
	public void setHallImageList(List<FileDTO> hallImageList) {
		this.hallImageList = hallImageList;
	}
	public String getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(String likeStatus) {
		this.likeStatus = likeStatus;
	}
	public List<ReviewDTO> getReviewList() {
		return reviewList;
	}
	public void setReviewList(List<ReviewDTO> reviewList) {
		this.reviewList = reviewList;
	}
	public List<HallQuestionDTO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<HallQuestionDTO> questionList) {
		this.questionList = questionList;
	}


	
	
}
