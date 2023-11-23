package com.ssafy.ssafit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.service.ReviewService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService rService;
	
		// 해당 영상의 리뷰 리스트
		@GetMapping("{videoId}")
		public ResponseEntity<?> reviewList(@PathVariable int videoId) {
			List<Review> list = rService.getReviewList(videoId);
			
			if (list == null || list.size() == 0)
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
		}

		// 해당 영상의 리뷰 상세 조회
		@GetMapping("detail/{reviewId}")
		public ResponseEntity<?> reviewDetail(@PathVariable int reviewId) {
			Review review = rService.getReviewOne(reviewId);
			
			if (review == null)
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		}

		// 해당 영상에 리뷰 등록
		@PostMapping
		public ResponseEntity<?> writeReview(@RequestBody Review review, HttpServletRequest request) {
			int result = rService.writeReview(review);
			
			if (result == 0)
				return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		}
		
		
		// 해당 영상의 리뷰 수정
		@PutMapping
		public ResponseEntity<?> updateReview(@RequestBody Review review) {
			int result = rService.modifyReview(review);
			
			if (result == 0)
				return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}

		// 해당 영상의 리뷰 삭제
		@DeleteMapping("{reviewId}")
		public ResponseEntity<?> deleteReview(@PathVariable int reviewId){
			int result = rService.deleteReview(reviewId);
			
			if(result == 0)
				return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
	
}
