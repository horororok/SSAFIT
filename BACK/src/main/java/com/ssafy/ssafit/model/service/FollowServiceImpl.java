package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.FollowDao;
import com.ssafy.ssafit.model.dto.Follow;

@Service
public class FollowServiceImpl implements FollowService{

	private FollowDao followdao;
	
	@Autowired
	public FollowServiceImpl(FollowDao followdao) {
		this.followdao = followdao;
	}
	
	
	
	@Override
	public int isFollowed(Follow follow) {
		return followdao.isFollowed(follow);
	}

	@Override
	public int follow(Follow follow) {
		return followdao.follow(follow);
	}

	@Override
	public int unfollow(Follow follow) {
		return followdao.unfollow(follow);
	}

}
