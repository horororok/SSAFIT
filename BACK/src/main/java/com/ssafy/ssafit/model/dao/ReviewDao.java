package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewDao {

	List<Review> selectAll(int id);
	
	Review selectOne(int id);
	
	int insert(Review review);
	
	int update(Review review);
	
	int delete(int id);
	
}
