package com.hobart.spring.tx;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(){
		String sql="insert into hb_user (name,age) values (?,?)";
		String name = UUID.randomUUID().toString().substring(0, 5);
		jdbcTemplate.update(sql, name,26);
	}
}
