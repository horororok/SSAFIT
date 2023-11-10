package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment;

public interface CommentDao {

	List<Comment> selectAll();

	int insert(Comment comment);

	int update(Comment comment);

	int delete(int id);

}
