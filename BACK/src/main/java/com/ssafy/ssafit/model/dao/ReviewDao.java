package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewDao {

	List<Review> selectAllReview(int id);
	
	Review selectReviewOne(int id);
	
	int insertReview(Review review);
	
	int updateReview(Review review);
	
	int deleteReview(int id);
	
	
}
