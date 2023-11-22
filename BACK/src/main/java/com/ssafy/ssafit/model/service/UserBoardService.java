package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.UserBoard;

public interface UserBoardService {

	
	//애초에 추가 정보 등록하는 화면에서 유저게시판에 보일 정보를 등록해주세요 라는 알림해주기
	//mypage 테이블에 추가 정보 등록되면 무조건 유저게시판에서 가져올 것 
	
	
	//전체 유저 목록 반환
	List<UserBoard> selectAll();

	//유저 한명 상세글 보기
	UserBoard selectOne(int user_id);
	
	//본인 제외한 모든 유저 목록 반환
	List<UserBoard> getFriendList(int user_id);
	
}