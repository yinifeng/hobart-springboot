package com.defu.meeting.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class BaseModel implements Serializable {
	private static final long serialVersionUID = 8135319949393669714L;
	private Date crt_time;
	private Date up_time;
	private String enable_flg;

	public Date getCrt_time() {
		return crt_time;
	}

	public void setCrt_time(Date crt_time) {
		this.crt_time = crt_time;
	}

	public Date getUp_time() {
		return up_time;
	}

	public void setUp_time(Date up_time) {
		this.up_time = up_time;
	}

	public String getEnable_flg() {
		return enable_flg;
	}

	public void setEnable_flg(String enable_flg) {
		this.enable_flg = enable_flg;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
