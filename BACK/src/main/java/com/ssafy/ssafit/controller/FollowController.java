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

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.dto.UserBoard;
import com.ssafy.ssafit.model.service.FollowService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/follow")
public class FollowController {
	
	@Autowired
	private FollowService fservice;
	
	//팔로우 상태 확인 (1이면 팔로우 한 상태, 0이면 팔로우 안 한 상태)
	@GetMapping
	public ResponseEntity<?> isFollowed(@RequestBody Follow follow){
		int isfollowed = fservice.isFollowed(follow);
		
		if(isfollowed == 0 || isfollowed == 1)
			return new ResponseEntity<Integer>(isfollowed, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

	//팔로우
	@PostMapping
	public ResponseEntity<?> follow(@RequestBody Follow follow){
		int result = fservice.follow(follow);
		System.out.println("팔로우" + follow);
		
		if(result == 1)
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//언팔로우
	@DeleteMapping
	public ResponseEntity<?> unfollow(@RequestBody Follow follow){
		int result = fservice.unfollow(follow);
		System.out.println("언팔로우" + follow);
		
		if(result == 1)
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//내가 팔로우하는 사람 목록 가져오기
	@GetMapping("following/{id}")
	public ResponseEntity<?> getFollowingList(@PathVariable int id){
		List<UserBoard> list = fservice.followingList(id);
		
		if(list == null || list.size() <= 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<UserBoard>>(list, HttpStatus.OK);
	}
	
	//나를 팔로우하는 사람 목록 가져오기
	@GetMapping("follower/{id}")
	public ResponseEntity<?> getFollowerList(@PathVariable int id){
		List<UserBoard> list = fservice.followerList(id);
		
		if(list == null || list.size() <= 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<UserBoard>>(list, HttpStatus.OK);
	}
	
	

}
