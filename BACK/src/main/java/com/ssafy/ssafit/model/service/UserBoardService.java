package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.UserBoard;

public interface UserBoardService {

	//전체 유저 목록 반환
	List<UserBoard> selectAll();

	//유저 한명 상세글 보기
	UserBoard selectOne(int user_id);
	
	//본인 제외한 모든 유저 목록 반환
	List<UserBoard> getFriendList(int user_id);
	
}
