package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.MyPageDao;
import com.ssafy.ssafit.model.dto.MyPage;
import com.ssafy.ssafit.model.dto.User;

@Service
public class MyPageServiceImpl implements MyPageService {

	private MyPageDao mypageDao;
	
	@Autowired
	public MyPageServiceImpl(MyPageDao mypageDao) {
		this.mypageDao = mypageDao;
	}
	
	
	@Override
	public User getMyUser(int id) {
		return mypageDao.getMyUser(id);
	}

	@Override
	public int modify(User user) {
		return mypageDao.updateUser(user);
	}

	@Override
	public int insertMyPage(MyPage mypage) {
		return mypageDao.insertMyPage(mypage);
	}

	@Override
	public MyPage getMyPageUser(int id) {
		return mypageDao.getMyPageUser(id);
	}

	@Override
	public int modifyMypage(MyPage mypage) {
		return mypageDao.updateMyPage(mypage);
	}

	@Override
	public int isRegist(int id) {
		return mypageDao.isRegist(id);
	}

}
