package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.VideoLike;

public interface VideoLikeDao {
	
	int isLiked(VideoLike videolike);
	
	int likeVideo(VideoLike videolike);
	
	int unlikeVideo(VideoLike videolike);
	
	int plusVideoLikeCnt(VideoLike videolike);
	
	int minusVideoLikeCnt(VideoLike videolike);
	
	
}
