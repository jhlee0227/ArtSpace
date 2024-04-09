package com.example.demo.file.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.company.dto.CompanyFileDTO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.HallImageDTO;

@Repository
public class FileDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public void insertFile(FileDTO fileDTO) {
        sqlSession.insert("file.insert", fileDTO);
    }

	public void insertCFile(CompanyFileDTO cFile) {
		sqlSession.insert("file.insertCFile", cFile);
	}

}
