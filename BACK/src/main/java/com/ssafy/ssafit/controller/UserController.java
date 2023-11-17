package com.ssafy.ssafit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.MyPage;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;

@RestController
@RequestMapping("/api-user")
public class UserController {

	@Autowired
	private UserService uService;

	@GetMapping("users")
	public ResponseEntity<?> userList() {
		List<User> users = uService.getUserList();

		if (users == null || users.size() < 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// id에 해당하는 사용자 정보 반환
	// 회원정보 상세 조회 (로그인할 때 사용)
	@GetMapping("user/{id}")
	public ResponseEntity<?> selectOne(@PathVariable String id) {
		User user = uService.searchById(id);

		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("signup")
	public ResponseEntity<?> signup(User user) {
		System.out.println(user);
		int result = uService.signup(user);

		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User temp = uService.login(user);

		// 로그인 실패
		if (temp == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		// 로그인 성공
		session.setAttribute("loginUser", temp);
		return new ResponseEntity<User>(temp, HttpStatus.OK);
	}

	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();

//		if(session != null)
//			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// --------------------마이페이지------------------

	// 마이페이지 회원 정보 보기 (본인 기본 정보)
	@GetMapping("/mypage/{userId}")
	public ResponseEntity<?> selectMyUser(@PathVariable int userId) {
		User user = uService.getMyUser(userId);

		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 회원정보 수정 (기본 정보)
	@PutMapping("/mypage")
	public ResponseEntity<?> update(@RequestBody User user) {
		int result = uService.modify(user);

		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	// 프론트에서 수정 페이지에 user_id로 해당 유저의 기존 정보 미리 세팅해두고
	// id 제외하고 모든 정보 수정 가능
	
	
	// 마이페이지에서 사용자 추가 정보 등록
	@PostMapping("/mypage/signup")
	public ResponseEntity<?> mysignup(@RequestBody MyPage mypage) {
		int result = uService.insertMyPage(mypage);
		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}	
	

	// 마이페이지 추가 정보 가져오기
	@GetMapping("/mypage/user/{userId}")
	public ResponseEntity<?> mypageUser(@PathVariable int userId){
		MyPage mypage = uService.getMyPageUser(userId);
		if(mypage == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<MyPage>(mypage, HttpStatus.OK);
	}
	
	// + 유저 소개 게시판에서 리스트로 가져오기, 상세 보기, 정렬 기능, 검색 기능 넣기

}
