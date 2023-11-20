package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.UserBoard;
import com.ssafy.ssafit.model.service.UserBoardService;

@RestController
@RequestMapping("/userboard")
public class UserBoardController {

	private UserBoardService  ubService;
	
	//유저 목록 가져오기
	@GetMapping("")
	public ResponseEntity<?> list(SearchCondition condition){
		List<UserBoard> list = ubService.search(condition);
		
		if(list == null || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserBoard>>(list, HttpStatus.OK);
	}
	
	//유저 상세 보기
	@GetMapping("/{id}")
	public ResponseEntity<?> detail(@PathVariable int id){
		UserBoard userBoard = ubService.getOneUser(id);
		if(userBoard == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserBoard>(userBoard, HttpStatus.OK);
	}
	
	
	
	
}
