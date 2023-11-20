package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.UserBoardDao;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.UserBoard;

@Service
public class UserBoardServiceImpl implements UserBoardService {

	private UserBoardDao userBoardDao;
	
	@Autowired
	public void setUserBoardDao(UserBoardDao userBoardDao) {
		this.userBoardDao = userBoardDao;
	}
	
	@Override
	public List<UserBoard> getUserList(){
		return  userBoardDao.selectAll();
	}
	
	

	@Override
	public UserBoard getOneUser(int id) {
		return  userBoardDao.selectOne(id);
	}

	@Override
	public List<UserBoard> search(SearchCondition condition) {
		return userBoardDao.search(condition);
	}


	
	
}
