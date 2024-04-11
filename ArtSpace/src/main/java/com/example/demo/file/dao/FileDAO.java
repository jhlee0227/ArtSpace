package com.example.demo.file.dao;

import java.util.List;
import java.util.Map;

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
	
	// 파일 아이디 리스트를 받으면 파일DTO List 리턴
	public List<FileDTO> getFileList(List<Integer> fileIDList) {
		return sqlSession.selectList("file.getFileList", fileIDList);
	}
	
	// 파일 아이디 리스트를 받으면 파일DTO List 리턴
	public FileDTO getHallMainFile(Integer hall_id) {
		return sqlSession.selectOne("file.getHallMainFile", hall_id);
	}
	

	public void deleteFiles(List<Integer> fileIDList) {
		sqlSession.delete("file.deleteFiles", fileIDList);
	}

	public List<Integer> getFileIDList(String[] deleteURL) {
		return sqlSession.selectList("file.getFile_ID", deleteURL);
	}

}
