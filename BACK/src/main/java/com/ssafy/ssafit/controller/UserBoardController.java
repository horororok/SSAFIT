package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.UserBoard;
import com.ssafy.ssafit.model.service.UserBoardService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/userboard")
public class UserBoardController {

	@Autowired
	private UserBoardService  ubService;
	
	//유저게시판 전체 목록
	@GetMapping("list")
	public ResponseEntity<?> getlist(){
		List<UserBoard> list = ubService.selectAll();
		
		if(list == null || list.size() < 0) 
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<UserBoard>>(list, HttpStatus.OK);	
	}
	
	//유저 한명 상세 보기
	@GetMapping("detail/{user_id}")
	public ResponseEntity<?> selectOne(@PathVariable int user_id){
		UserBoard userboard = ubService.selectOne(user_id);
		
		if(userboard == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<UserBoard>(userboard, HttpStatus.OK);
	}
	
	
	
}
