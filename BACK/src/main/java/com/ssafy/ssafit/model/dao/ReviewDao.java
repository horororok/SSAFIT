package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewDao {

    Review selectOne(int id);

    List<Review> selectAll();

    int insert(Review review);

    int update(Review review);

    int delete(int id);


}