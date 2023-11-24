package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.dto.UserBoard;

public interface FollowDao {
	
	int follow(Follow follow);
	
	int unfollow(Follow follow);

	List<UserBoard> getFollowingList(int id);

	List<UserBoard> getFollowerList(int id);
	
}
