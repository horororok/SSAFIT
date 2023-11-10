package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CommentDao;
import com.ssafy.ssafit.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao;
	
	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao=commentDao;
	}
	
	//댓글 리스트
	@Override
	public List<Comment> listComment() {
		return commentDao.selectAll();
	}

	//댓글 등록
	@Override
	public int writeComment(Comment comment) {
		return commentDao.insert(comment);
	}

	//댓글 수정
	@Override
	public int modifyComment(Comment comment) {
		return commentDao.update(comment);
	}

	//댓글 삭제
	@Override
	public int deleteboard(int id) {
		return commentDao.delete(id);
	}
	

}
