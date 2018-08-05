package com.defu.meeting.model;


public class Seat extends BaseModel{
	private static final long serialVersionUID = 8932720349153154570L;
	
	private Long id;
	private String seatName;
	private String status;//座位状态 0:未分配 1：已分配
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
