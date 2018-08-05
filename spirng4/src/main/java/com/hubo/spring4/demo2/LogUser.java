package com.hubo.spring4.demo2;

public class LogUser extends User{
	@Override
	public void show() {
		System.out.println("start......");
		super.show();
		System.out.println("stop......");
	}
}
