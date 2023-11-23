package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.MyPage;
import com.ssafy.ssafit.model.dto.User;

public interface MyPageDao {

	User getMyUser(int id);

	int updateUser(User user);
	
	int insertMyPage(MyPage mypage);
	
	MyPage getMyPageUser(int id);
	
	int updateMyPage(MyPage mypage);
	
	int isRegist(int id);
	
}
