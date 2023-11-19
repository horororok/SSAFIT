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
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.VideoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api-video")
public class VideoController {

	@Autowired
	private VideoService vService;

	// 목록 가져오기 (검색 조건에 맞는, 조건 없으면 전체 영상 가져옴)
	@GetMapping("/video")
	public ResponseEntity<?> list(SearchCondition condition) {
		List<Video> list = vService.search(condition);

		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

	// 영상 상세보기
	@GetMapping("/video/{id}")	public ResponseEntity<?> detail(@PathVariable int id) {
		Video video = vService.getVideo(id);
		if (video == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

	// 영상 등록하기
	@PostMapping("/video")
	public ResponseEntity<?> write(@RequestBody Video video) {
		int result = vService.writeVideo(video);
		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// 영상 수정하기
	@PutMapping("/video")
	public ResponseEntity<?> update(@RequestBody Video video) {
		int result = vService.modifyVideo(video);
		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// 영상 삭제하기
	@DeleteMapping("/video/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		int result = vService.deleteVideo(id);

		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	//----------------------------리뷰 관련-------------------------------
	
	
	// 해당 영상의 리뷰 리스트
	@GetMapping("/video/review/{videoId}")
	public ResponseEntity<?> reviewList(@PathVariable int videoId) {
		List<Review> list = vService.getReviewList(videoId);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	// 해당 영상의 리뷰 상세 조회
	@GetMapping("/video/review/detail/{reviewId}")
	public ResponseEntity<?> reviewDetail(@PathVariable int reviewId) {
		Review review = vService.getReviewOne(reviewId);
		if (review == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}

	// 해당 영상에 리뷰 등록
	@PostMapping("/video/review")
	public ResponseEntity<?> writeReview(@RequestBody Review review, HttpServletRequest request) {
//		System.out.println(review);
		int result = vService.writeReview(review);
		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	
	// 해당 영상의 리뷰 수정
	@PutMapping("/video/review")
	public ResponseEntity<?> updateReview(@RequestBody Review review) {
		System.out.println(review);
		int result = vService.modifyReview(review);
		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// 해당 영상의 리뷰 삭제
	@DeleteMapping("/video/review/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable int reviewId){
		int result = vService.deleteReview(reviewId);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	
}
