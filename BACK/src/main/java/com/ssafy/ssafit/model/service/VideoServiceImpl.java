package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService{

	private VideoDao videoDao;
	
	@Autowired
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	public List<Video> getList(int user_id) {
		return videoDao.selectAll(user_id);
	}

	//상세보기 할 경우 조회수 증가
	@Override
	public Video getVideo(Map<String, Object> map) {
		videoDao.updateView((int)map.get("video_id"));
		return videoDao.selectOne(map);
	}

	@Override
	public List<Video> search(SearchCondition condition) {
		return videoDao.search(condition);
	}

	@Override
	public int writeVideo(Video video) {
		return videoDao.insert(video);
	}

	@Override
	public int modifyVideo(Video video) {
		return videoDao.update(video);
	}

	@Override
	public int deleteVideo(int id) {
		return videoDao.delete(id);
	}

	@Override
	public int updateView(int id) {
		return videoDao.updateView(id);
	}

	
	//--------------리뷰
	
	@Override
	public List<Review> getReviewList(int videoId) {
		return videoDao.selectAllReview(videoId);
	}

	@Override
	public Review getReviewOne(int reviewId) {
		return videoDao.selectReviewOne(reviewId);
	}

	@Override
	public int writeReview(Review review) {
		return videoDao.insertReview(review);
	}

	@Override
	public int modifyReview(Review review) {
		return videoDao.updateReview(review);
	}

	@Override
	public int deleteReview(int id) {
		return videoDao.deleteReview(id);
	}
	
	

}
