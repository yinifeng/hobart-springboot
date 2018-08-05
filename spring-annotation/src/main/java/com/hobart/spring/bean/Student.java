package com.hobart.spring.bean;

import org.springframework.beans.factory.annotation.Value;

public class Student {
	//使用@Value注解标注属性
	//1、注解中直接赋值
	//2、spEL表达式：#{}
	//3、获取配置文件注入到容器的配置值:${}
	
	@Value("张三")
	private String name;
	@Value("#{30-2}")
	private Integer age;
	@Value("${student.nickName}")
	private String nickName;

	public Student() {
	}

	public Student(String name, Integer age, String nickName) {
		this.name = name;
		this.age = age;
		this.nickName = nickName;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", nickName=" + nickName + "]";
	}

}
