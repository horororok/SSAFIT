package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.VideoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/video")
public class VideoController {

	@Autowired
	private VideoService vService;

	//영상 전체 목록 반환
	@GetMapping("/list/{user_id}")
	public ResponseEntity<?> getList(@PathVariable int user_id){
		List<Video> list = vService.getList(user_id);
		System.out.println(list);
		
		if(list == null || list.size() <= 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
	
	
	// 검색정렬
	@GetMapping("/search")
	public ResponseEntity<?> list(SearchCondition condition, @RequestParam int user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("user_id", user_id);
		
		List<Video> list = vService.search(map);

		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

	// 영상 상세보기
	@GetMapping("/{video_id}")	
	public ResponseEntity<?> detail(@PathVariable int video_id, @RequestParam int user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("video_id", video_id);
		map.put("user_id", user_id);
		
		Video video = vService.getVideo(map);
		
		if (video == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}
	
}
