package com.defu.meeting.service;

import java.util.List;

import com.defu.meeting.model.Seat;

public interface SeatService {
	
	List<Seat> listSeat();
	
	boolean addSeat(Seat seat);
}
