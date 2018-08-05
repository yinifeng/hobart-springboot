package com.defu.meeting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.defu.meeting.model.Seat;

public interface SeatDao {
	
	int insert(Seat seat);
	
	List<Seat> findAll();
	
	int updateSeat(Seat seat);
	
	Seat findSeatById(@Param("id") Long id);
	
	List<Seat> findByStatus(@Param("status") String status);
	
}
