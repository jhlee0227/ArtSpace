package com.example.demo.hall.dto;

import java.util.List;

public class HallFilterDTO {
	
	private List<String> localList;
	private String minPrice;
	private String maxPrice;
	private String maxPeople;
	private String sort;
	
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
