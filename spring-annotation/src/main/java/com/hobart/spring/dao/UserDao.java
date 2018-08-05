package com.hobart.spring.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private String labe="1";

	public String getLabe() {
		return labe;
	}

	public void setLabe(String labe) {
		this.labe = labe;
	}

}
