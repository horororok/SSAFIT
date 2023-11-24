package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDao;
	
	@Autowired
	public ReviewServiceImpl(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	

	@Override
	public List<Review> getReviewList(int videoId) {
		return reviewDao.selectAllReview(videoId);
	}

	@Override
	public Review getReviewOne(int reviewId) {
		return reviewDao.selectReviewOne(reviewId);
	}

	@Override
	public int writeReview(Review review) {
		return reviewDao.insertReview(review);
	}

	@Override
	public int modifyReview(Review review) {
		return reviewDao.updateReview(review);
	}

	@Override
	public int deleteReview(int id) {
		return reviewDao.deleteReview(id);
	}
	
}
