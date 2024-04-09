package com.example.demo.file.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.HallImageDTO;

@Repository
public class FileDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public void insertFile(FileDTO fileDTO) {
        sqlSession.insert("file.insert", fileDTO);
    }

	// 파일 아이디 리스트를 받으면 파일DTO List 리턴
	public List<FileDTO> getFileList(List<Integer> fileIDList) {
		return sqlSession.selectList("file.getFileList", fileIDList);
	}

	public void deleteFiles(List<Integer> fileIDList) {
		sqlSession.delete("file.deleteFiles", fileIDList);
	}

}
