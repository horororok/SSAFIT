package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.MyPage;
import com.ssafy.ssafit.model.dto.User;


public interface UserService {
		
		//회원 리스트 반환
		List<User> getUserList();
		
		//회원가입
		int signup(User user);
	
		//로그인
		User login(User user);

		//id로 사용자 정보 반환
		User searchById(String id);
		
		//회원정보 수정 (아이디 제외 .. 프론트에서 read only..?)
		int modify(User user);
		
		//마이페이지에 추가 정보 등록
		int insertMyPage(MyPage mypage);
		
//		//마이페이지 기본 정보 + 추가 정보 하나 가져오기 (user_id로) -> 유저 소개 게시판 서비스로 옮기기
//		UserMyPage getMyPageUser(int id);
		
		//마이페이지 기본 정보 가져오기
		User getMyUser(int id);
		
		MyPage getMyPageUser(int id);
}
