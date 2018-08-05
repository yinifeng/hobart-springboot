package com.hubo.spring4.demo1;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	public void show() {
		System.out.println("Hello UserDao!!!!");
	}
}
