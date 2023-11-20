package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.VideoLike;

public interface VideoLikeService {
	
	//좋아요 했는지 안했는지 확인 (videoLike 테이블에 존재하면 이미 좋아요 한 것)
	public int isLiked(VideoLike videolike);
	
	//좋아요 
	public int likeVideo(VideoLike videolike);
	
	//좋아요 취소
	public int unlikeVideo(VideoLike videolike);
	
	//내가 좋아요 한 영상 목록 가져오기
	public List<Video> getLikeList(int user_id);
	
	
}
