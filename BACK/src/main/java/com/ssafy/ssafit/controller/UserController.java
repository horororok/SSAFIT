package com.ssafy.ssafit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;


@RestController
@RequestMapping("/api-user")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@GetMapping("users")
	public ResponseEntity<?> userList(){
		List<User> users = uService.getUserList();
		
		if(users == null || users.size()<0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PostMapping("signup")
	public ResponseEntity<?> signup(User user){
		int result = uService.signup(user);
		
		//회원가입할 때 이미 기존 회원중에 같은 아이디 있으면 회원가입 못하게 알림 (프론트에서 데이터 받고 거기서 처리?)
		
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(User user, HttpSession session){
		User temp = uService.login(user);
		
		//로그인 실패
		if(temp == null)
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		//로그인 성공
		session.setAttribute("loginUser", temp.getName());
		return new ResponseEntity<String>(temp.getName(), HttpStatus.OK);
	}
	
	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session){
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
