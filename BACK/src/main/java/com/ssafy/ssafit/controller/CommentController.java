package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.service.CommentService;


@RestController
@RequestMapping("/api-comment")
public class CommentController {
	
	//CommentService 주입
	@Autowired
	private CommentService commentService;
	
	//댓글 목록 가져오기
	@GetMapping("/comment")
	public ResponseEntity<?> list(){
		List<Comment> list = commentService.listComment();
		if(list==null || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
	}
	
	//댓글 등록
	@PostMapping("/comment")
	public ResponseEntity<?> write(Comment comment){
		int result = commentService.writeComment(comment);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	//댓글 수정
	@PutMapping("/comment")
	public ResponseEntity<?> update(Comment comment){
		int result = commentService.modifyComment(comment);
		if(result ==0) {
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	//댓글 삭제
	@DeleteMapping("/comment/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		int result = commentService.deleteboard(id);
		if(result==0) {
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	
}
