package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.MyPage;
import com.ssafy.ssafit.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User login(User user) {
		User tmp = userDao.selectOne(user.getId());
		if(tmp != null && tmp.getPassword().equals(user.getPassword())) 
			return tmp;
		
		return null; //틀리면 null 반환
	}

	@Override
	public User searchById(String id) {
		return userDao.selectOne(id);
	}

	@Override
	public int modify(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int insertMyPage(MyPage mypage) {
		return userDao.insertMyPage(mypage);
	}

	@Override
	public MyPage getMyPageUser(int id) {
		return userDao.getMyPageUser(id);
	}

	@Override
	public User getMyUser(int id) {
		return userDao.getMyUser(id);
	}

	@Override
	public int isRegist(int id) {
		return userDao.isRegist(id);
	}
	
	

}
