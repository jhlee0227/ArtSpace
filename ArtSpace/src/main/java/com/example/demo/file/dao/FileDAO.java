package com.example.demo.file.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.file.FileDTO;

@Repository
public class FileDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public void insertFile(FileDTO fileDTO) {
        sqlSession.insert("file.insert", fileDTO);
    }
}
