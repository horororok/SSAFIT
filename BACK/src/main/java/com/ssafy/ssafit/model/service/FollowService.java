package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowService {
	
	//팔로우 했는지 안했는지
	public int isFollowed(Follow follow);
	
	//팔로우
	public int follow(Follow follow);
	
	//언팔로우
	public int unfollow(Follow follow);
	
}
