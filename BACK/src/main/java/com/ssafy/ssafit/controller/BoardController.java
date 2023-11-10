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

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Board;
import com.ssafy.ssafit.model.service.BoardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-board")
public class BoardController {

	@Autowired
	private BoardService bService;
	
	@ApiOperation(value = "게시글 목록 보기", notes = "검색 조건에 맞는 목록 반환, 조건 없으면 전체 목록 보기")
	@GetMapping("/board")
	public ResponseEntity<?> list(SearchCondition condition){
		List<Board> list = bService.search(condition);
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 상세 보기", notes="해당 글 상세 보기")
	@GetMapping("/board/{id}")
	public ResponseEntity<?> detail(@PathVariable int id){
		Board board = bService.getBoard(id);
		if(board == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 등록 하기", notes = "게시글 하나 등록 하기")
	@PostMapping("/board")
	public ResponseEntity<?> write(Board board){
		int result = bService.writeBoard(board);
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 수정하기", notes = "글 제목,내용 수정 (작성자 수정 불가)")
	@PutMapping("/board")
	public ResponseEntity<?> update(Board board){
		int result = bService.modifyBoard(board);
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 삭제하기", notes = "해당 글 삭제하기")
	@DeleteMapping("/board/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		int result = bService.deleteBoard(id);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
