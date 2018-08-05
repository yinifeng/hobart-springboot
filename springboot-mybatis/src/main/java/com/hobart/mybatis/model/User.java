package com.hobart.mybatis.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -3191700766645481767L;
	private Integer id;
	private String name;
	private Integer age;
	private String email;
	private String addr;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", addr=" + addr + "]";
	}

}
