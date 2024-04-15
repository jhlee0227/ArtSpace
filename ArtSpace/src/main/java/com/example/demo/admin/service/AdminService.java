package com.example.demo.admin.service;

import java.util.List;

import com.example.demo.announcement.dto.NoticeDto;
import com.example.demo.company.dto.CompanyDTO;
import com.example.demo.company.dto.CompanyFileDTO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.user.dto.UserDTO;

public interface AdminService {

	public List<UserDTO> getAllUsers();

	public void leave(Integer user_id);

	public void resign(Integer user_id);

//	public List<UserDTO> searchUsersByEmail(String keyword);
//	
//	public List<UserDTO> searchUsersByNickname(String keyword);
	
	public List<HallDTO> getAllHalls();

	public List<NoticeDto> getAllNotice();

	public List<CompanyDTO> getCompany();

	public List<UserDTO> searchUsers(String type, String keyword);

	public List<HallDTO> getHalls(String type, String keyword);

	public NoticeDto findNoticeById(Integer id);

	public void hallBlock(Integer hall_id);

	public void hallUnblock(Integer hall_id);

	public void noticeDelete(Integer announ_id);

	public void approve(Integer user_id);

	public int findCompanyById(Integer user_id);

	public void unapprove(Integer company_id);

	public List<CompanyFileDTO> getCompanyFile(int companyId);

}
