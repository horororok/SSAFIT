package com.ssafy.ssafit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
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

	@GetMapping("{id}")
	public ResponseEntity<?> selectOne(@PathVariable String id) {
		User user = uService.searchById(id);

		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
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

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
