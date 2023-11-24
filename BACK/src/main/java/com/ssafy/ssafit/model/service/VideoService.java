package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Video;

public interface VideoService {
	
	//비디오 리스트 반환
	List<Video> getList(int user_id);
	
	//비디오 상세보기
	Video getVideo(Map<String, Object> map);

	//검색 조건에 맞는 비디오만 반환
	List<Video> search(Map<String, Object> map);
	
	//조회수 증가
	int updateView(int id);
	
}