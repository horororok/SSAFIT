package com.ssafy.ssafit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.VideoLike;
import com.ssafy.ssafit.model.service.VideoLikeService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api-vlike")
public class VideoLikeController {
	
	@Autowired
	private VideoLikeService vlikeservice;
	
	
	//좋아요 눌렀는지 안눌렀는지 확인 (userid랑 videoid 조건으로 해서 개수 가져오기)
	//cnt 1이면 이미 좋아유 누름, 0이면 좋아요 아직 안누름
	@GetMapping
	public ResponseEntity<?> isLiked(@RequestBody VideoLike videolike){
		int isliked = vlikeservice.isLiked(videolike);
		
		if(isliked == 0 || isliked == 1) 
			return new ResponseEntity<Integer>(isliked, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	//좋아요
	@PostMapping
	public ResponseEntity<?> likeVideo(@RequestBody VideoLike videolike){
		int result = vlikeservice.likeVideo(videolike); 
		
		if(result == 1) {
//			System.out.println("좋아요했다" + videolike);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
			
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//좋아요취소
	@DeleteMapping
	public ResponseEntity<?> unlikeVideo(@RequestBody VideoLike videolike){
		int result = vlikeservice.unlikeVideo(videolike);
		
		if(result == 1) {
//			System.out.println("좋아요취소했다" + videolike);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
			
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
