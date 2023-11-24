package com.ssafy.ssafit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.MyPage;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.MyPageService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	private MyPageService mService;
	
	@GetMapping("{userId}")
	public ResponseEntity<?> selectMyUser(@PathVariable int userId) {
		User user = mService.getMyUser(userId);

		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 회원정보 수정 (기본 정보)
	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user) {
		int result = mService.modify(user);

		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	// 마이페이지에서 사용자 추가 정보 등록
	@PostMapping("signup")
	public ResponseEntity<?> mysignup(@RequestBody MyPage mypage) {
		int result = mService.insertMyPage(mypage);
		
		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}	

	// 마이페이지 추가 정보 가져오기
	@GetMapping("user/{userId}")
	public ResponseEntity<?> mypageUser(@PathVariable int userId){
		MyPage mypage = mService.getMyPageUser(userId);

		if(mypage == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<MyPage>(mypage, HttpStatus.OK);
	}
	
	//마이페이지 추가 정보 수정하기
	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody MyPage mypage){
		int result = mService.modifyMypage(mypage);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	//내가 추가 정보 등록했는지 안했는지 확인
	@GetMapping("isregist/{id}")
	public ResponseEntity<?> isMyPageRegist(@PathVariable int id){
		int result = mService.isRegist(id);
		
		if(result == 1 || result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
