package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewService {

    //해당 영상의 전체 리뷰 반환
    List<Review> getList();

    //리뷰 상세보기
    Review getReviewOne (int id);

    //리뷰 등록
    int writeReview(Review review);

    //리뷰 수정
    int modifyReview(Review review);

    //리뷰 삭제
    int deleteReview(int id);

}