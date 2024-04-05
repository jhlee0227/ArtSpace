package com.example.demo.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReservationDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.mypage.dto.LikeDTO;
import com.example.demo.mypage.dto.PerformerDTO;
import com.example.demo.user.dto.UserDTO;

@Repository
public class MypageDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	public UserDTO findById(Integer id) {
		return sqlSession.selectOne("mypage.findById", id);
	}

	public void updateNickname(UserDTO dto) {
		sqlSession.update("mypage.updateNickname", dto);
		
	}

	public void updatePw(UserDTO dto) {
		sqlSession.update("mypage.updatePw", dto);
		
	}

	public PerformerDTO findByPID(Integer id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mypage.findByPID", id);
	}


	public void insertPerformer(PerformerDTO dto) {
		sqlSession.insert("mypage.insertPerformer", dto);
	}

	public void updatePerformer(PerformerDTO dto) {
		sqlSession.update("mypage.updatePerformer", dto);
	}

	public void leave(UserDTO dto) {
		sqlSession.update("mypage.leave", dto);
		
	}

	public List<HallDTO> getAllLike(Integer id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mypage.getLike", id);
	}

	public void likeDelete(Integer user_id, Integer hall_id) {
		Map<String, Integer> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("hall_id", hall_id);
		sqlSession.update("mypage.likeDelete", map);
	}

	public List<ReservationDTO> getAllReserve(Integer user_id) {
		return sqlSession.selectList("mypage.getReserve", user_id);
	}

//	public void reserveDelete(Integer user_id, Integer hall_id) {
//		Map<String, Integer> map = new HashMap<>();
//		map.put("user_id", user_id);
//		map.put("hall_id", hall_id);
//		sqlSession.update("mypage.reserveDelete", map);
//	}
	
	public void reserveDelete(Integer reserve_id) {
		
		sqlSession.update("mypage.reserveDelete", reserve_id);
	}

	public ReservationDTO reserveDetail(Integer reserve_id) {
		
		return sqlSession.selectOne("mypage.reserveDetail", reserve_id);
	}

	public List<ReservationDTO> reserveEquip(Integer reserve_id) {
		
		return sqlSession.selectList("mypage.reserveEquip", reserve_id);
	}

	public void updatePhone(UserDTO dto) {
		sqlSession.update("mypage.updatePhone", dto);
		
	}

	public List<HallDTO> getNotReview(Integer user_id) {

		return sqlSession.selectList("mypage.getNotReview", user_id);
	}

	public List<ReviewDTO> getReview(Integer user_id) {
		
		return sqlSession.selectList("mypage.getReview", user_id);
	}

	public void saveReview(ReviewDTO review) {
	    sqlSession.insert("mypage.saveReview", review);
	    
	}

	public void updateReviewStatus(Integer reserve_id) {
		sqlSession.update("mypage.updateReviewStatus", reserve_id);
		
	}

}
