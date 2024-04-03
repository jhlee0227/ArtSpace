package com.example.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CSVReader {
	public static void main(String[] args) {
		CSVReader c = new CSVReader();
		c.readCsv();
	}
	
	
	public void readCsv(){
		// 추후 return 할 데이터 목록
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		
	    // return data key 목록
	    List<String> headerList = new ArrayList<String>();
	    
	    try{
	    	BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/static/csv/region.csv"));
	        String line = "";
	        System.out.println(br.readLine());
	        System.out.println(br.readLine());

	        
	        while((line = br.readLine()) != null){
				List<String> stringList = new ArrayList<String>();
				String stringArray[] = line.split(",");
				stringList = Arrays.asList(stringArray);
	            
				// csv 1열 데이터를 header로 인식
				if(headerList.size() == 0){
					headerList = stringList;
				} else {
	 				Map<String, String> map = new HashMap<String, String>();
					// header 컬럼 개수를 기준으로 데이터 set
					for(int i = 0; i < headerList.size(); i++){
						map.put(headerList.get(i), stringList.get(i));
					}
					mapList.add(map);
				}
			}
	        System.out.println(mapList);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    //return new ResponseEntity<>(mapList, HttpStatus.OK);
	}
    
    
}