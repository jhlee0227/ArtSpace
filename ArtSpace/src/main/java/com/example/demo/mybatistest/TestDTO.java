package com.example.demo.mybatistest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

	private String test1;
	
	private String test2;
	
	@Override
	public String toString() {
		return "test1 " + test1 + "test2 " + test2;
	}
	

}
