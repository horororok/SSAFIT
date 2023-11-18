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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api-review")
public class ReviewController {
	
	@Autowired
	private ReviewService rService;
	
	//해당 영상의 전체 리뷰 반환 (id: video_id)
	@ApiOperation(value = "리뷰 목록 보기", notes = "전체 리뷰 반환") 
	@GetMapping("/review/list/{id}") 
	public ResponseEntity<?> list(@PathVariable int id){
		List<Review> list = rService.getReviewList(id);
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	//리뷰 한 개 상세 보기 (id :review_id)
	@ApiOperation(value = "리뷰 상세 보기", notes="해당 리뷰 상세 보기")
	@GetMapping("/review/{id}")
	public ResponseEntity<?> detail(@PathVariable int id){
		Review review = rService.getReviewOne(id);
		if(review == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	@ApiOperation(value = "리뷰 등록 하기", notes = "리뷰 하나 등록 하기")
	@PostMapping("/review")
	public ResponseEntity<?> write(Review review){
		int result = rService.writeReview(review);
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "리뷰 수정하기", notes = "내용 수정")
	@PutMapping("/review")
	public ResponseEntity<?> update(Review review){
		int result = rService.modifyReview(review);
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	//리뷰 삭제하기 (id : review_id)
	@ApiOperation(value = "리뷰 삭제하기", notes = "해당 리뷰 삭제하기")
	@DeleteMapping("/review/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		int result = rService.deleteReview(id);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
