package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Video;

public interface VideoDao {
	
	List<Video> selectAll(int user_id);
	
	Video selectOne(Map<String, Object> map);

	List<Video> search(Map<String, Object> map);

	int updateView(int id);
}
