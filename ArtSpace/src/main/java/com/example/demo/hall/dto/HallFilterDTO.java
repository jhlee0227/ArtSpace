package com.example.demo.hall.dto;

import java.util.List;

public class HallFilterDTO {
	// 리스트 필터용 DTO
	
	private List<String> localList;	// 지역리스트 
	private String minPrice;		// 최소가격
	private String maxPrice;		// 최대가격
	private String maxPeople;		// 인원수
	private String sort;			// 정렬기준
	
	public List<String> getLocalList() {
		return localList;
	}
	public void setLocalList(List<String> localList) {
		this.localList = localList;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(String maxPeople) {
		this.maxPeople = maxPeople;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	

}
