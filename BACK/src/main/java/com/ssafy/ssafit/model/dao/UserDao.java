package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.User;

public interface UserDao {
	
	List<User> selectAll();
	
	int insertUser(User user);
	
	User selectOne(String id);
	
}
