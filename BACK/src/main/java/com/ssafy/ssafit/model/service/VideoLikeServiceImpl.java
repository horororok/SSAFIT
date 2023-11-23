package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoLikeDao;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.VideoLike;

@Service
public class VideoLikeServiceImpl implements VideoLikeService {

	private VideoLikeDao vlikedao;
	
	@Autowired
	public VideoLikeServiceImpl(VideoLikeDao vlikedao) {
		this.vlikedao = vlikedao;
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

	@Override
	public List<Video> getLikeList(int user_id) {
		return vlikedao.getLikeList(user_id);
	}

}
