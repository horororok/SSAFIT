package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Board;
import com.ssafy.ssafit.model.dto.SearchCondition;

public interface BoardDao {

	Board selectOne(int id);

	List<Board> selectAll();

	int insert(Board board);

	int update(Board board);

	int delete(int id);
	
	void updateViewCnt(int id);
	
	List<Board> search(SearchCondition condition);
	
}
