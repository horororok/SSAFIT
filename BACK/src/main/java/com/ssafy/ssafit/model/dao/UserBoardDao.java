package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.UserBoard;

public interface UserBoardDao {
	
	List<UserBoard> selectAll();

	UserBoard selectOne(int user_id);
	
	
}
