package com.hubo.spring4.demo1;

/**
 * 自定义工厂
 * @author hubo
 *
 */
public class CarFactory {
	public Car create(){
		return new Car();
	}
}
