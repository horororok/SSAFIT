package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.User;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDao;
	
	@Autowired
	public ReviewServiceImpl(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	
	
	@Override
	public List<Review> getReviewList(int id) {
		return reviewDao.selectAll(id);
	}

	@Override
	public Review getReviewOne(int id) {
		return reviewDao.selectOne(id);
	}
	
	@Override
	public int writeReview(Review review) {
		return reviewDao.insert(review);
	}

	@Override
	public int modifyReview(Review review) {
		return reviewDao.update(review);
	}

	@Override
	public int deleteReview(int id) {
		return reviewDao.delete(id);
	}



//	@Override
//	public Review getnicknameById(int id) {
//		return reviewDao.getnicknameById(id);
//	}






	

}
