package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoLikeDao;
import com.ssafy.ssafit.model.dto.VideoLike;

@Service
public class VideoLikeServiceImpl implements VideoLikeService {

	private VideoLikeDao vlikedao;
	
	@Autowired
	public VideoLikeServiceImpl(VideoLikeDao vlikedao) {
		this.vlikedao = vlikedao;
	}
	
	@Override
	public int isLiked(VideoLike videolike) {
		return vlikedao.isLiked(videolike);
	}

	@Override
	public int likeVideo(VideoLike videolike) {
		vlikedao.plusVideoLikeCnt(videolike);
		return vlikedao.likeVideo(videolike);
	}

	@Override
	public int unlikeVideo(VideoLike videolike) {
		vlikedao.minusVideoLikeCnt(videolike);
		return vlikedao.unlikeVideo(videolike);
	}

}
