package com.hobart.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class Boss {
	private Car car;
	
	//如果只有一个有参构造这个注解可以省略
	@Autowired 
	public Boss(Car car) {
		this.car=car;
	}
	public Car getCar() {
		return car;
	}
	
	//@Autowired
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Boss [car=" + car + "]";
	}
	
	
}
