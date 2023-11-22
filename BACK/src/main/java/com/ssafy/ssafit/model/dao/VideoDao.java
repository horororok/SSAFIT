package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;

public interface VideoDao {
	
	List<Video> selectAll(int user_id);
	
	Video selectOne(Map<String, Object> map);

	List<Video> search(Map<String, Object> map);
	
	int insert(Video video);
	
	int update(Video video);
	
	int delete(int id);

	int updateView(int id);
	
	//-----------------리뷰--------------------
	
	List<Review> selectAllReview(int id);
	
	Review selectReviewOne(int id);
	
	int insertReview(Review review);
	
	int updateReview(Review review);
	
	int deleteReview(int id);
	
	
	
}
