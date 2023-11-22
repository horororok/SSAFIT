package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;

public interface VideoService {
	
	//비디오 리스트 반환
	List<Video> getList(int user_id);
	
	//비디오 상세보기
	Video getVideo(Map<String, Object> map);

	//검색 조건에 맞는 비디오만 반환
	List<Video> search(SearchCondition condition);

	//영상 등록
	int writeVideo(Video video);
	
	//영상 수정
	int modifyVideo(Video video);
	
	//영상 삭제
	int deleteVideo(int id);
	
	//조회수 증가
	int updateView(int id);
	
	//------------------리뷰--------------------
	
	//해당 영상의 전체 리뷰 가져오기
	List<Review> getReviewList(int videoId);
	
	//해당 리뷰 상세보기
	Review getReviewOne(int reviewId);
	
	//리뷰 등록
	int writeReview(Review review);
	
	//리뷰 수정
	int modifyReview(Review review);
		
	//리뷰 삭제
	int deleteReview(int id);
	
	
	
}