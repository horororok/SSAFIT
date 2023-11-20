package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowDao {
	
	int isFollowed(Follow follow);
	
	int follow(Follow follow);
	
	int unfollow(Follow follow);
	
}
