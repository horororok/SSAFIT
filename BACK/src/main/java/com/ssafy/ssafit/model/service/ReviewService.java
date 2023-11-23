package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewService {
	
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
