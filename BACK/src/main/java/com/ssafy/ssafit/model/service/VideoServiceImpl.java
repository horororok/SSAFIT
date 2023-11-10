package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoDao;
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
	public List<Video> getList() {
		return videoDao.selectAll();
	}

	@Override
	public Video getVideo(int id) {
		return videoDao.selectOne(id);
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

}
