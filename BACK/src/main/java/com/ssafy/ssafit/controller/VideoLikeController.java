package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.VideoLike;
import com.ssafy.ssafit.model.service.VideoLikeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vlike")
public class VideoLikeController {
	
	@Autowired
	private VideoLikeService vlikeservice;
	
	//좋아요
	@PostMapping
	public ResponseEntity<?> likeVideo(@RequestBody VideoLike videolike){
		int result = vlikeservice.likeVideo(videolike); 
		
		if(result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//좋아요취소
	@DeleteMapping
	public ResponseEntity<?> unlikeVideo(@RequestBody VideoLike videolike){
		int result = vlikeservice.unlikeVideo(videolike);
		
		if(result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	//내가 좋아요 한 영상 목록 가져오기
	@GetMapping("{user_id}")
	public ResponseEntity<?> getLikeList(@PathVariable int user_id){
		List<Video> likeList = vlikeservice.getLikeList(user_id);
		
		if(likeList == null || likeList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(likeList, HttpStatus.OK);
	}
	
}
