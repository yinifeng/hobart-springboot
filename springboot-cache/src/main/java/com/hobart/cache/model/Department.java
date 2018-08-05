package com.hobart.cache.model;

import java.io.Serializable;

public class Department implements Serializable{

	private static final long serialVersionUID = -3120704533870752805L;
	
	private Integer id;
	
	private String deptName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + "]";
	}
	
}
