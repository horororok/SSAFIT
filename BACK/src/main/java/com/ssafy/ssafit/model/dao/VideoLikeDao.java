package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.VideoLike;

public interface VideoLikeDao {
	
	int isLiked(VideoLike videolike);
	
	int likeVideo(VideoLike videolike);
	
	int unlikeVideo(VideoLike videolike);
	
	int plusVideoLikeCnt(VideoLike videolike);
	
	int minusVideoLikeCnt(VideoLike videolike);

	List<Video> getLikeList(int user_id);
	
	
}
