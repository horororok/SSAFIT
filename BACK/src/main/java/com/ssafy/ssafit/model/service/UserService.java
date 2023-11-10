package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.User;


public interface UserService {
		
		//회원 리스트 반환
		List<User> getUserList();
		
		//회원가입
		int signup(User user);
	
		//로그인
		User login(User user);
}
