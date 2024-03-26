package com.example.demo;

import java.util.UUID;

public class UUIDgeneration {
    public String getUUID() {
        
        //UUID 생성
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        
        // "-" 하이픈 제외
        uuid = uuid.replace("-", "");
        System.out.println(uuid);
        return uuid;
        
    	
		// 사용할때
//		UUIDgeneration uuid = new UUIDgeneration();
//		hallDTO.setHall_id(uuid.getUUID());

    }
}