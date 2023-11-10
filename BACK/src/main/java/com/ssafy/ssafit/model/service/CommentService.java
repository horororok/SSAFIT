package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment;

public interface CommentService {

	//댓글 리스트
	List<Comment> listComment();
	
	//댓글 등록
	int writeComment(Comment comment);

	//댓글 수정
	int modifyComment(Comment comment);

	//댓글 삭제
	int deleteboard(int id);


	


}
