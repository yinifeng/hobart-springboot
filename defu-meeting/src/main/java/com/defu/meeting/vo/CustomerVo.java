package com.defu.meeting.vo;

public class CustomerVo {
	private String id;
	private String name;
	private String tel;
	private String sex;
	private String company;
	private String siginStatus;
	private String seatId;
	private String seatName;
	private String siginStatusName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public String getSiginStatusName() {
		if("0".equals(siginStatus)){
			this.siginStatusName="未签";
		}else if("1".equals(siginStatus)){
			this.siginStatusName="已签";
		}else if("2".equals(siginStatus)){
			this.siginStatusName="现场增加";
		}
		return this.siginStatusName;
	}

	public void setSiginStatusName(String siginStatusName) {
		this.siginStatusName = siginStatusName;
	}

	@Override
	public String toString() {
		return "CustomerVo [id=" + id + ", name=" + name + ", tel=" + tel + ", sex=" + sex + ", company=" + company
				+ ", siginStatus=" + siginStatus + ", seatId=" + seatId + ", seatName=" + seatName + "]";
	}
	
}
