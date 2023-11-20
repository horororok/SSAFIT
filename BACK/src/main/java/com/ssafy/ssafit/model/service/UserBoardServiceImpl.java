package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.UserBoardDao;
import com.ssafy.ssafit.model.dto.UserBoard;

@Service
public class UserBoardServiceImpl implements UserBoardService {

	private UserBoardDao userBoardDao;
	
	@Autowired
	public UserBoardServiceImpl(UserBoardDao userBoardDao) {
		this.userBoardDao = userBoardDao;
	}
	
	

	@Override
	public List<UserBoard> selectAll() {
		return userBoardDao.selectAll();
	}

	@Override
	public UserBoard selectOne(int user_id) {
		return userBoardDao.selectOne(user_id);
	}

	
	


	
	
}
