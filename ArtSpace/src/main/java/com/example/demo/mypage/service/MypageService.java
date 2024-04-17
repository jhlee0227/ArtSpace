package com.example.demo.mypage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.mypage.dto.LikeDTO;
import com.example.demo.mypage.dto.PerformerDTO;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReservationEquipmentDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;
import com.example.demo.user.dto.UserDTO;

public interface MypageService {

	public UserDTO findByID(Integer id);
	
	public PerformerDTO findByPID(Integer id);

	public void updateNickname(UserDTO dto);

	public void updatePw(UserDTO dto);

	public void insert(PerformerDTO dto);

	public void leave(UserDTO dto);

	public List<HallDTO> getAllLike(Integer id);

	public void likeDelete(Integer user_id, Integer hall_id);

	public List<ReservationDTO> getAllReserve(Integer user_id);
	
	public Map<Integer, LocalDate> getEarliestReserveDates(List<ReservationDTO> reservationList);
	
	public void reserveDelete(Integer reserve_id);

	public ReservationDTO reserveDetail(Integer reserve_id);

	public void updatePhone(UserDTO dto);

	public List<ReservationDTO> getNotReview(Integer user_id);

	public List<ReviewDTO> getReview(Integer user_id);
	
	public void saveReview(ReviewDTO review);

	public void updateReviewStatus(Integer reserve_id);

	public List<ReservationEquipmentDTO> getAllReservationEquip(Integer reserve_id);

	public List<ReserveDateDTO> reserveDate(Integer reserve_id);



}
