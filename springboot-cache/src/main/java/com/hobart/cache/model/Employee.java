package com.hobart.cache.model;

import java.io.Serializable;

public class Employee implements Serializable{

	private static final long serialVersionUID = 3584126199134888543L;
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private Integer age;
	
	private Integer sex;
	
	private Integer dId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", sex=" + sex + ", dId="
				+ dId + "]";
	}
	
}
