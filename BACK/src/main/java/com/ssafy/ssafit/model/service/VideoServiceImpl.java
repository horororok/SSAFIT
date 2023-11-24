package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoDao;
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

	@Override
	public Video getVideo(Map<String, Object> map) {
		videoDao.updateView((int)map.get("video_id"));
		return videoDao.selectOne(map);
	}

	@Override
	public List<Video> search(Map<String, Object> map) {
		return videoDao.search(map);
	}

	@Override
	public int updateView(int id) {
		return videoDao.updateView(id);
	}	

}
