package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.MyPage;
import com.ssafy.ssafit.model.dto.User;

public interface MyPageService {

	//마이페이지 기본 정보 반환
	User getMyUser(int id);
	
	//회원정보 수정
	int modify(User user);
	
	//마이페이지에 추가 정보 등록
	int insertMyPage(MyPage mypage);
	
	//마이페이지 추가 정보 반환
	MyPage getMyPageUser(int id);
	
	//마이페이지 추가 정보 수정
	int modifyMypage(MyPage mypage);
	
	//추가정보 등록했는지 확인
	int isRegist(int id);
	
}
