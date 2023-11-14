package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Board;
import com.ssafy.ssafit.model.dto.Bookmark;

public interface BookMarkService {
	
	//해당 유저의 북마크 리스트 가져오기 (user_id)
	public List<Board> getBookMarkList(int id);
	
	//해당 게시글 북마크 등록
	public int insertBookMark(Bookmark bookmark);
	
	//해당 게시글 북마크 삭제 
	public int deleteBookMark(Bookmark bookmark);
	
}
