package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminDAO;
import com.example.demo.announcement.dto.NoticeDto;
import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.company.dto.CompanyFileDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.user.dto.UserDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public List<UserDTO> getAllUsers() {

		return adminDAO.getAllUsers();
	}

	@Override
	public void leave(Integer user_id) {
		adminDAO.leave(user_id);

	}

	@Override
	public void resign(Integer user_id) {
		adminDAO.resign(user_id);

	}

	@Override
	public List<HallDTO> getAllHalls() {
		
		return adminDAO.getAllHalls();
	}

	@Override
	public List<NoticeDto> getAllNotice() {
		return adminDAO.getAllNotice();
	}

	@Override
	public List<CompanyDTO> getCompany() {
		
		return adminDAO.getCompany();
	}

	@Override
	public List<UserDTO> searchUsers(String type, String keyword) {
        return adminDAO.searchUsers(type, keyword);
    }

	@Override
	public List<HallDTO> getHalls(String type, String keyword) {
		
		return adminDAO.getHalls(type, keyword);
	}

	@Override
	public NoticeDto findNoticeById(Integer id) {
		
		return adminDAO.findNoticeById(id);
	}

	@Override
	public void hallBlock(Integer hall_id) {
		
		adminDAO.hallBlock(hall_id);
	}

	@Override
	public void hallUnblock(Integer hall_id) {
		adminDAO.hallUnblock(hall_id);
	}

//	@Override
//	public List<CompanyFileDTO> getCompanyFile(int companyId) {
//		
//		return adminDAO.getCompanyFile(companyId);
//	}

	
}
