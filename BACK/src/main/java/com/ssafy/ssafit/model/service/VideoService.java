package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;

public interface VideoService {
	
	//비디오 리스트 반환
	List<Video> getList();
	
	//비디오 상세보기
	Video getVideo(int id);

	//검색 조건에 맞는 비디오만 반환
	List<Video> search(SearchCondition condition);

	//영상 등록
	int writeVideo(Video video);
	
	//영상 수정
	int modifyVideo(Video video);
	
	//영상 삭제
	int deleteVideo(int id);
	
}