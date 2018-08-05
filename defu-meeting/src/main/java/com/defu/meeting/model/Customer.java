package com.defu.meeting.model;


/**
 * 客人表
 * 
 * @author hobart
 *
 */
public class Customer extends BaseModel{

	private static final long serialVersionUID = -4781801786452391394L;
	private Long id;
	private String name;
	private String tel;
	private String sex;
	private String company;
	private String siginStatus;//签到状态 0：未签到 1：已签到 2：现场增加
	private Long seatId;// 座位号
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSiginStatus() {
		return siginStatus;
	}
	public void setSiginStatus(String siginStatus) {
		this.siginStatus = siginStatus;
	}
	public Long getSeatId() {
		return seatId;
	}
	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

}
