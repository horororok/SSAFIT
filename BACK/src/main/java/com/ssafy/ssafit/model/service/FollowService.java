package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.dto.UserBoard;

public interface FollowService {
	
	//팔로우
	public int follow(Follow follow);
	
	//언팔로우
	public int unfollow(Follow follow);
	
	//내가 팔로우하는 사람 목록
	public List<UserBoard> followingList(int id);

	//나를 팔로우하는 사람 목록
	public List<UserBoard> followerList(int id);
	
}
