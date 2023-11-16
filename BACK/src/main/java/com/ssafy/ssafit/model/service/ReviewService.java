package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.User;

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
	
	
//	//리뷰에 있는 user_id(작성자)로 user 테이블이랑 join 해서 닉네임 가져오기  (Review의 user_id)
//	public Review getnicknameById(int id); 
	
}
