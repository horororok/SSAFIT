package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewService {
	
	//해당 영상의 리뷰 전체 가져오기 (video_id)
	List<Review> getReviewList(int id);
	
	//리뷰 상세보기 (review_id)
	Review getReviewOne (int id);
	
	//리뷰 등록
	int writeReview(Review review);
	
	//리뷰 수정
	int modifyReview(Review review);
	
	//리뷰 삭제
	int deleteReview(int id);
	
	
	
}
