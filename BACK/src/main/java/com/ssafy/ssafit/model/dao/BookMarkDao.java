package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Board;
import com.ssafy.ssafit.model.dto.Bookmark;

public interface BookMarkDao {
	
	public List<Board> getBookMarkList(int id);
	
	public int insertBookMark(Bookmark bookmark);
	
	public int deleteBookMark(Bookmark bookmark);
	
}
