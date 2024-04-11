package com.example.demo.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.announcement.dto.NoticeDto;
import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.company.dto.CompanyFileDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.user.dto.UserDTO;

@Repository
public class AdminDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("admin.getAllUsers");
	}

	public void leave(Integer user_id) {

		sqlSession.update("admin.leave", user_id);
	}

	public void resign(Integer user_id) {

		sqlSession.update("admin.resign", user_id);
	}

	public List<HallDTO> getAllHalls() {
		
		return sqlSession.selectList("admin.getAllHalls");
	}

	public List<NoticeDto> getAllNotice() {
		
		return sqlSession.selectList("admin.getAllNotice");
	}

	public List<CompanyDTO> getCompany() {
		
		return sqlSession.selectList("admin.getCompany");
	}

	public List<UserDTO> searchUsers(String type, String keyword) {
		Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("keyword", keyword);
		return sqlSession.selectList("admin.searchUsers", params);
	}

	public List<HallDTO> getHalls(String type, String keyword) {
		Map<String, String> params = new HashMap<>();
		params.put("type", type);
		params.put("keyword", keyword);
		return sqlSession.selectList("admin.getHalls", params);
	}

	public NoticeDto findNoticeById(Integer id) {
		
		return sqlSession.selectOne("admin.findNoticeById", id);
	}

	public void hallBlock(Integer hall_id) {
		sqlSession.update("admin.hallBlock", hall_id);
	}

	public void hallUnblock(Integer hall_id) {
		sqlSession.update("admin.hallUnblock", hall_id);
	}

//	public List<CompanyFileDTO> getCompanyFile(int companyId) {
//		
//		return sqlSession.selectList("admin.getCompanyFile", companyId);
//	}


}
