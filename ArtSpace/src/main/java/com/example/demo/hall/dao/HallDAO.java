package com.example.demo.hall.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hall.dto.EquipmentDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.HallImageDTO;
import com.example.demo.hall.dto.HallTimeDTO;
import com.example.demo.mypage.dto.LikeDTO;

@Repository
public class HallDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	

	//	SELECT	//
	// 공연장 아이디로 해당 공연장 정보 찾아옴
	public HallDTO findById(Integer id) {
		return sqlSession.selectOne("hall.findById", id);
	}

	// 공연장 리스트 가져옴(정렬 기준)
	public List<HallDTO> getHallList() {
		return sqlSession.selectList("hall.getHallList");
	}

	// 공연장 시간 정보 리스트를 가져옴
	public List<HallTimeDTO> getHallTimeList(Integer id) {
		return sqlSession.selectList("hall.getHallTimeList", id);
	}
	
	// 장비 정보 리스트 가져옴
	public List<EquipmentDTO> getEquiList(Integer id) {
		return sqlSession.selectList("hall.getEquiList", id);
	}
	
	// 지역 필터 걸어서 공연장 리스트 가져옴
	public List<HallDTO> getHallFilterList(Map<String, Object> filter) {
		return sqlSession.selectList("hall.getFilterList", filter);
	}
	
	// 첫 사진(대표이미지) 가져오기
	public Integer getFirstImgFile(Integer hall_id) {
		return sqlSession.selectOne("hallFile.select_first_file", hall_id);
	}

	public int likeCheck(LikeDTO like) {
		return sqlSession.selectOne("hall.likeCheck", like);
	}
	
	public String getHallLikeStatus(LikeDTO like) {
		return sqlSession.selectOne("hall.getLikeStatus", like);
	}
	
	//	SELECT END	//

	
	// INSERT //	
	// 공연장 기본정보 insert
	public void insert(HallDTO hallDTO) {
		sqlSession.insert("hall.insert", hallDTO);
	}
	
	// 공연장 시간 정보 insert
	public void insertHallTime(HallTimeDTO hallTimes) {
		sqlSession.insert("hall.insert_time", hallTimes);		
	}
	
	// 공연장 장비 정보 insert
	public void insertEquiList(EquipmentDTO equiDTO) {
		sqlSession.insert("hall.equi_insert", equiDTO);
	}
	
	// 공연장 찜 정보 insert
	public void likeHall(LikeDTO like) {
		sqlSession.insert("hall.like_insert", like);		
	}
	// INSERT END //


	//	DELETE //
	// 모든 장비 정보를 삭제함(form 입력받는 구간에서만 사용함)
	public void deleteAllEquiList(Integer id) {
		sqlSession.delete("hall.equi_delete_all", id);
	}

	// 공연장 기본 정보를 삭제함 (form에서 등록 취소할 때만 사용함)
	public void deleteHall(Integer id) {
		sqlSession.delete("hall.delete", id);		
	}
	
	public void deleteAllTime(Integer id) {
		sqlSession.delete("hall.deleteAllTime", id);
	}
	
	public void deleteHallTime(Integer id) {
		sqlSession.delete("hall.deleteTime", id);
		
	}
	
	
	//	DELETE END //



	// UPDATE //
	// 공연장 기본 정보 update
	public void update(HallDTO hallDTO) {
		sqlSession.update("hall.update_info", hallDTO);
	}
	
	// 공연장 시간 정보 update
	public void updateHallTime(HallTimeDTO hallTimes) {
		sqlSession.update("hall.update_hall_time",  hallTimes);
	}
	
	public void visibilityTrue(Integer hall_id) {
		sqlSession.update("hall.post_visibility_true",  hall_id);			
	}

	public void visibilityFalse(Integer hall_id) {
		sqlSession.update("hall.post_visibility_false",  hall_id);			
	}

	public void updateLikeHall(LikeDTO like) {
		sqlSession.update("hall.update_like",  like);			
	}


	
	
	// UPDATE END //






	
	//	========================================

	

}
